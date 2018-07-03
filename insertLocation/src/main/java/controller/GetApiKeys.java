/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cuongvm
 */
public class GetApiKeys {
    public List getApiKeys(String filePath) throws FileNotFoundException{
        List<String> apiKey = new ArrayList();
        Scanner s = new Scanner(new File(filePath));
        while(s.hasNext())
            apiKey.add(s.next());
        return apiKey;
    }
}
