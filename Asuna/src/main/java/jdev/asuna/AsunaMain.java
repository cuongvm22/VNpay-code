/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.asuna;

import com.restfb.types.Url;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

/**
 *
 * @author cuongvm
 */
public class AsunaMain {
    public static void main(String[] args) throws MalformedURLException, IOException {
        String token = "1701403853271971|9zLna8f6j31_ta6YQrj-XEyajDc";
        String urlString = MessageFormat.format("https://graph.facebook.com/v2.3/me/inbox?access_token={0}&&format=json&method=get",token);
        URL u = new URL(urlString); 
        BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
        String line = "";
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
                
    }
}
