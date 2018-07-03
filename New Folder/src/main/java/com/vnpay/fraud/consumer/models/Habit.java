/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.fraud.consumer.models;

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
public class Habit {
    int avg_txn_day;
    int avg_txn_week;
    int avg_txn_month;
    int avg_txn_amount;
    String txn_time_do;

    
    
    
}
