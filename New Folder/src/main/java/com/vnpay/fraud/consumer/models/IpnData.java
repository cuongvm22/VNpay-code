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
public class IpnData {

    private long tranId;
    private String tmnCode;
    private String rspCode;
    private String callType;
    private String data;
    private String url;
    private String version;

}
