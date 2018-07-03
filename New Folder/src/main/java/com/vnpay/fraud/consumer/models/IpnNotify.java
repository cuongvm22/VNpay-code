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
 * @desc System notification
 */
@Getter
@Setter
@Data
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class IpnNotify {

    String rsp_code;
    String rsp_msg;
    String  add_data;
    Date created_date;

}

