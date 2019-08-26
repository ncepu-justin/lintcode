package com.company;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created by Justin
 * 2019/8/26  19:56
 */
public class Base64 {


    public String base64Encoder(String src) throws UnsupportedEncodingException {
        BASE64Encoder encoder = new BASE64Encoder();
        final byte[] textByte = src.getBytes("UTF-8");
        String encodedText = encoder.encode(textByte);
        System.out.println(encodedText);
        return encodedText;
    }

    public static void main (String[] args) throws UnsupportedEncodingException {
        Base64 base64=new Base64();
        base64.base64Encoder("Man");
    }
}
