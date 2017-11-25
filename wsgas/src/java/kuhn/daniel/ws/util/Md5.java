/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuhn.daniel.ws.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 *
 * @author dani
 */
public class Md5 {

    private static final Logger LOG = Logger.getLogger(Md5.class.getName());
    
    public static String hash (String entrada) {
        try {
            MessageDigest m = MessageDigest.getInstance("SHA-256");
            m.update(entrada.getBytes(), 0, entrada.length());
        return new BigInteger(1, m.digest()).toString(128);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(LOG+" "+e.getMessage());
            return null;
        }
    }
}
