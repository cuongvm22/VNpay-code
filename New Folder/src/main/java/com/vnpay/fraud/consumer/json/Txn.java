/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.fraud.consumer.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author xonv
 */
@Getter
@Setter
@Data
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown=true)
public class Txn {

    private long tranId;

    private String tmnCode;

    private String bankCode;

    private String tranType;

    private String cardType;

    private String cardHash;

    private String maskCardNumber;

    private long ordid;

    private String txnRef;

    private String ordCatgr;

    private long amount;

    private String currCode;

    private String txnData;

    private String payType;

    private Date payDate;

    private String status;

    private String bankStatus;

    private Date expDate;

    private String cusId;

    private long ipaddr;

    private String countryCode;

    private String fdStatus;

    private String decision;

    private String sysNote;

    private String userAgent;

    private Date createdDate;

    private Date modifiedDate;

    private String modifiedBy;
}
