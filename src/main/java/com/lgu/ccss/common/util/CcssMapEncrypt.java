package com.lgu.ccss.common.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class CcssMapEncrypt {
	
	public static String key ="cd0c3839865d4e2693be817785553677";
	
	public static void main(String[] args) {
		System.out.println("핸드폰번호:" + encryptAES256(key,"01089897264"));
		System.out.println("출발지 위도:" + encryptAES256(key,"126.962272"));
		System.out.println("출발지 경도:" + encryptAES256(key,"37.533949"));
		System.out.println("도착지 위도:" + encryptAES256(key,"126.963947"));
		System.out.println("도착지 경도:" + encryptAES256(key,"37.529583"));
	}


	public static String encryptAES256(String key, String string) {
	String KEY_256 = key.substring(0, 256 / 8);
	String KEY_128 = key.substring(0, 128 / 8);
	
	try {
	      byte[] key256Data = KEY_256.getBytes("UTF-8");
	      byte[] key128Data = KEY_128.getBytes("UTF-8");
	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	      cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key256Data, "AES"), new IvParameterSpec(key128Data));
	byte[] encrypted = cipher.doFinal(string.getBytes("UTF-8"));
	      byte[] base64Encoded = Base64.getEncoder().encode(encrypted);
	String result = new String(base64Encoded, "UTF-8");
	return result;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
}



