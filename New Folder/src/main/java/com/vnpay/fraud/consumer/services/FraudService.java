/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.fraud.consumer.services;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import static org.neo4j.driver.v1.Values.parameters;

/**
 *
 * @author xonv
 */
public class FraudService {

    public void test(String[] args) {
        System.out.println("Begin app start");
        String uri = "bolt://192.168.3.130:7687";
        String user = "neo4j";
        String pwd = "admin123";
        String message = "HELLO";
        Driver driver;
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, pwd));
        try (Session session = driver.session()) {
            String greeting = session.writeTransaction((Transaction tx) -> {
                StatementResult result = tx.run("CREATE (a:Greeting) "
                        + "SET a.message = $message "
                        + "RETURN a.message + ', from node ' + id(a)",
                        parameters("message", "HELLO"));
                return result.single().get(0).asString();
            });
            System.out.println(greeting);
        }
        driver.close();
        System.out.println("End app");
    }
}
