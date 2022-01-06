/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conf;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author dell
 */
public class HashUtils {
     public static String hashPassword(String mk) throws UnsupportedEncodingException, NoSuchAlgorithmException{ 
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((new String(mk)).getBytes("UTF8"));
        String s = new String(md.digest());
        return s;
    }
}
