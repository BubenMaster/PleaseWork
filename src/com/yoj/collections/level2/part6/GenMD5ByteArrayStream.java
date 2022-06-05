package com.yoj.collections.level2.part6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

public class GenMD5ByteArrayStream {
    public static void main(String[] args) {
        ByteArrayOutputStream bos;
        ObjectOutputStream oos;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject("test string");
            oos.flush();
            System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean compareMD5 (ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        StringBuffer hex = new StringBuffer();
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(byteArrayOutputStream.toByteArray());
        byte[] bytes = digest.digest();
        for (int i = 0; i < bytes.length; i++) {
            String t =  Integer.toHexString (bytes[i] & 0xFF);
            hex.append(t);
        }
        return md5.equals(hex.toString());
    }

}
