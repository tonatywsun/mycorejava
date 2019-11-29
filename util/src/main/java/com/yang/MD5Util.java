package com.yang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 * 
 * @author tonasun
 *
 */
public class MD5Util {

	private final static Logger logger = LoggerFactory.getLogger(MD5Util.class);

	/**
	 * MD5加密
	 * 
	 * @param str 加密内容
	 * @return
	 */
	public final static String md5(String str) {
		try {
			byte[] strtemp = str.getBytes();
			MessageDigest mdtemp = MessageDigest.getInstance("MD5");
			mdtemp.update(strtemp);
			byte[] md = mdtemp.digest();
			return byteToHEX(md);
		} catch (Exception e) {
			logger.info("md5 exception", e);
			return null;
		}
	}

	/**
	 * MD5加密两次
	 * 
	 * @param 加密内容
	 * @return
	 */
	public final static String smd5(String str) {
		return md5(md5(str));
	}

	/**
	 * convert bytes to hex string
	 * 
	 * @param bytes
	 * @return
	 */
	private static String byteToHEX(byte[] bytes) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		StringBuffer sb = new StringBuffer();
		char[] ob = new char[2];
		for (int i = 0; i < bytes.length; i++) {
			byte byte0 = bytes[i];
			ob[0] = Digit[(byte0 >>> 4) & 0X0F];
			ob[1] = Digit[byte0 & 0X0F];
			sb.append(ob);
		}
		return sb.toString();
	}

}
