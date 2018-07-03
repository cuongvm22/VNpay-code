/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.fraud.consumer.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnpay.fraud.consumer.App;
import com.vnpay.fraud.consumer.models.IpnData;
import com.vnpay.fraud.consumer.models.IpnNotify;
import java.io.IOException;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Value;
import static org.neo4j.driver.v1.Values.parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author xonv
 */
public class IpnNotifyActor extends AbstractActor {

    final static Logger log = LoggerFactory.getLogger("fraudapi");

    static Props props(String inputMsg) {
        return Props.create(IpnNotifyActor.class, () -> new IpnNotifyActor());
    }
    static ObjectMapper mapper = new ObjectMapper();

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(ActorMessage.class, am -> processIpn(am)).matchAny(ua -> {
            log.warn("Match Any: {}", ua);
        }).build();
    }

    void processIpn(ActorMessage am) {
        try {
            //Create graph node
            String uri = App.props.getProperty("neo4j.uri");
            String user = App.props.getProperty("neo4j.user");
            String pwd = App.props.getProperty("neo4j.pwd");
            Driver driver;
            driver = GraphDatabase.driver(uri, AuthTokens.basic(user, pwd));
            //IPN Notify
            if ("ipn_notify".equals(am.getMsg_type())) {
                IpnNotify ipn = mapper.readValue(am.getMsg_content(), IpnNotify.class);
                IpnData data = mapper.readValue(ipn.getAdd_data(), IpnData.class);
                System.out.println("Convert ok:" + ipn.getRsp_code() + "," + data.getData());

                try (Session session = driver.session()) {
                    String cypherTxn = "MATCH (txn:Txn{tran_id:{tran_id}})";
                    String cypherIpn = "CREATE (ipn:IpnData {title:{title} ,tran_id:{tran_id},rsp_code:{rsp_code},tmn_code:{tmn_code},last_call:timestamp()})";
                    String cypherHas = "CREATE (txn)-[:HAS_IPN]->(ipn)";
                    Value prams = parameters("title", "IpnData", "tran_id", data.getTranId(), "rsp_code", data.getRspCode(), "tmn_code", data.getTmnCode());
                    session.run(cypherTxn + cypherIpn + cypherHas, prams);
                    log.info("Create IPN node success:{}", data.getTranId());
                }

            }
            //TxnNotify
            if ("txn_verify".equals(am.getMsg_type())) {
                com.vnpay.fraud.consumer.json.Txn txn = mapper.readValue(am.getMsg_content(), com.vnpay.fraud.consumer.json.Txn.class);
                System.out.println("Input verify:" + am.getMsg_content());
                try (Session session = driver.session()) {
//                    String cypherTxn = "CREATE (txn:Txn {title:{title},tran_id:{tran_id},tmn_code:{tmn_code},"
//                            + "bank_code:{bank_code},ord_type:{ord_type},txn_ref:{txn_ref},amount:{amount},"
//                            + "created_date:{created_date},last_update:timestamp(),status:{status},bank_status:{bank_status}})\n";
                    String cypherTxn = "MERGE (txn:Txn {tran_id:{tran_id}})"
                            + "ON CREATE SET txn.title={title},txn.tran_id={tran_id},txn.tmn_code={tmn_code},"
                            + "txn.bank_code={bank_code},txn.ord_type={ord_type},txn.txn_ref={txn_ref},txn.amount={amount},"
                            + "txn.created_date={created_date},txn.last_update=timestamp(),txn.status={status},txn.bank_status={bank_status} \n"
                            + "ON MATCH SET txn.last_update=timestamp(),txn.status={status},txn.bank_status={bank_status}";

                    String cypherIP = "MERGE (ip:IpAddr { iplong:{ip_iplong} }) "
                            + "ON CREATE SET ip.title={ip_title}, ip.ipaddr={ip_ipaddr},ip.iplong={ip_iplong}, ip.created_date = timestamp()\n"
                            + "ON MATCH SET ip.last_actvt = timestamp()\n";
                    String cypherCard = "";
                    if (txn.getCardHash() != null && !"".equals(txn.getCardHash())) {
                        cypherCard = "MERGE (card:Card{card_hash:{card_hash}})"
                                + "ON CREATE SET card.title={card_title},card.card_hash={card_hash},card.card_mask={card_mask},card.card_holder={card_holder},card.created_date=timestamp()\n"
                                + "ON MATCH SET card.last_actvt=timestamp()\n"
                                + "MERGE (card)-[:HAS_TXN]->(txn)\n"
                                + "MERGE (ip)-[:HAS_CARD]->(card)\n";
                    }

                    String cypherHas = "MERGE (ip)-[:HAS_TXN]->(txn)\n";
                    Value params;
                    if (txn.getCardHash() != null && !"".equals(txn.getCardHash())) {
                        params = parameters(
                                //TXN
                                "title", "Transaction", "tran_id", txn.getTranId(), "tmn_code",
                                txn.getTmnCode(), "bank_code", txn.getBankCode(), "ord_type", txn.getOrdCatgr(),
                                "txn_ref", txn.getTxnRef(), "amount", txn.getAmount(),
                                "created_date", txn.getCreatedDate().getTime(), "status", txn.getStatus(), "bank_status", txn.getBankStatus(),
                                //IP
                                "ip_title", "IpAddr", "ip_ipaddr", com.vnpay.common.Utils.longToIp(txn.getIpaddr()), "ip_iplong", txn.getIpaddr(),
                                "card_title", "CARD", "card_hash", txn.getCardHash(), "card_mask", txn.getMaskCardNumber(), "card_holder", "CARDHOLDER"
                        );
                    } else {
                        params = parameters(
                                //TXN
                                "title", "Transaction", "tran_id", txn.getTranId(), "tmn_code",
                                txn.getTmnCode(), "bank_code", txn.getBankCode(), "ord_type", txn.getOrdCatgr(),
                                "txn_ref", txn.getTxnRef(), "amount", txn.getAmount(),
                                "created_date", txn.getCreatedDate().getTime(), "status", txn.getStatus(), "bank_status", txn.getBankStatus(),
                                //IP
                                "ip_title", "IpAddr", "ip_ipaddr", com.vnpay.common.Utils.longToIp(txn.getIpaddr()), "ip_iplong", txn.getIpaddr()
                        );
                    }

                    session.run(cypherTxn + cypherIP + cypherCard + cypherHas, params);
                    log.info("Create Graph Txn Success, TranId={}", txn.getTranId());
                }

            }
            //Update Txn Status
            if ("txn_update_status".equals(am.getMsg_type())) {
                com.vnpay.fraud.consumer.json.Txn txn = mapper.readValue(am.getMsg_content(), com.vnpay.fraud.consumer.json.Txn.class);
                try (Session session = driver.session()) {
                    //create success
                    String cypherTxn = "MATCH (txn:Txn{tran_id:{tran_id}}) SET txn.last_update=timestamp(),txn.bank_status={rsp_code}\n";
                    String cypherVerify = "CREATE (verify:TXN_DETAIL{title:{title},tran_id:{tran_id},rsp_code:{rsp_code},rsp_msg:{rsp_msg},created_date:timestamp()})\n";
                    String cypherVer = "CREATE (txn)-[:VERIFY]->(verify)\n";
                    Value params = parameters("tran_id", txn.getTranId(), "title", "Verify", "rsp_code", txn.getBankStatus(), "rsp_msg", txn.getSysNote());
                    session.run(cypherTxn + cypherVerify + cypherVer, params);
                }

            }
            //Update Txn Confirm
            if ("txn_confirm".equals(am.getMsg_type())) {
                com.vnpay.fraud.consumer.json.Txn txn = mapper.readValue(am.getMsg_content(), com.vnpay.fraud.consumer.json.Txn.class);
                try (Session session = driver.session()) {
                    String status;
                    if ("00".equals(txn.getStatus())) {
                        status = "00";
                    } else {
                        status = "02";
                    }
                    //create success
                    String cypherTxn = "MATCH (txn:Txn{tran_id:{tran_id}}) SET txn.last_update=timestamp(),txn.status={status},txn.bank_status={rsp_code}\n";
                    String cypherVerify = "CREATE (cfm:TXN_DETAIL{title:{title},tran_id:{tran_id},rsp_code:{rsp_code},rsp_msg:{rsp_msg},created_date:timestamp()})\n";
                    String cypherVer = "CREATE (txn)-[:CONFIRM]->(cfm)\n";
                    Value params = parameters("tran_id", txn.getTranId(), "title", "Confirm", "rsp_code", txn.getStatus(), "rsp_msg", txn.getSysNote(), "status", status
                    );
                    session.run(cypherTxn + cypherVerify + cypherVer, params);
                }
            }

            log.info("topic:{},\n content:{}", am.getMsg_type(), am.getMsg_content());
            driver.close();
        } catch (IOException ex) {
            log.error("Process IPN Excetion: {}", ex);
            System.err.println(ex);
        }
    }
}
