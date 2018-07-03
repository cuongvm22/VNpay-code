/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.fraud.consumer.models;

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
 * @desc Bank for monitoring
 */
@Getter
@Setter
@Data
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Bank {

    String bank_code;
    Date created_date;
    Date last_txn_succ;
    Date last_txn_err;

     
    
}
