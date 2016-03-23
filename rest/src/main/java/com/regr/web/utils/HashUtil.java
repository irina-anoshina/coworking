package com.regr.web.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by maratische on 23.03.16.
 */
public class HashUtil {

    /**
     * рассчитывает MD5 хешь от строки
     * @param str
     * @return
     */
    public static String calcMd5Hash(String str)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            byte[] mdbytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte mdbyte : mdbytes)
            {
                //Конвертация байтов в 16-ричные строковые знач-я
                sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)   /* Не должно выпасть */
        {
            throw new RuntimeException(e);
        }
    }
}
