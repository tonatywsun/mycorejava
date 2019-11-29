package com.yang;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密工具类
 * 
 * @author tonasun
 *
 */
@SuppressWarnings("restriction")
public class RSAUtil {
	private static final int MAX_ENCRYPT_BLOCK = 117;

	// 生成秘钥对
	public static KeyPair getKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		return keyPair;
	}

	// 获取公钥(Base64编码)
	public static String getPublicKey(KeyPair keyPair) {
		PublicKey publicKey = keyPair.getPublic();
		byte[] bytes = publicKey.getEncoded();
		return byte2Base64(bytes);
	}

	// 获取私钥(Base64编码)
	public static String getPrivateKey(KeyPair keyPair) {
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] bytes = privateKey.getEncoded();
		return byte2Base64(bytes);
	}

	// 将Base64编码后的公钥转换成PublicKey对象
	public static PublicKey string2PublicKey(String pubStr) throws Exception {
		byte[] keyBytes = base642Byte(pubStr);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	// 将Base64编码后的私钥转换成PrivateKey对象
	public static PrivateKey string2PrivateKey(String priStr) throws Exception {
		byte[] keyBytes = base642Byte(priStr);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	// 公钥加密
	public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int inputLen = content.length;
		int offSet = 0;
		byte[] cache = null;
		int i = 0;
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(content, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(content, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	// 私钥解密
	public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytes = cipher.doFinal(content);
		return bytes;
	}

	// 字节数组转Base64编码
	public static String byte2Base64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	// Base64编码转字节数组
	public static byte[] base642Byte(String base64Key) throws IOException {
		return Base64.decodeBase64(base64Key);
	}

	public static void main(String[] args) throws Exception {
		String content = "mynameissunyangyang";
		KeyPair keyPair = getKeyPair();
		String publicKey = getPublicKey(keyPair);
		// System.out.println("publicKey:\r\n" + publicKey);
		String privateKey = getPrivateKey(keyPair);
		// System.out.println("privateKey:\r\n" + privateKey);
		System.out.println("加密前内容:" + content);
		String privateContent = RSAUtil
				.byte2Base64(RSAUtil.publicEncrypt(base642Byte(content), RSAUtil.string2PublicKey(publicKey)));
		System.out.println("加密后内容:" + privateContent);
		String publicContent = RSAUtil
				.byte2Base64(RSAUtil.privateDecrypt(base642Byte(privateContent), string2PrivateKey(privateKey)));
		System.out.println("解密后内容:" + publicContent);
	}
}
