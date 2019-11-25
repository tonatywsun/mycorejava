package com.yang.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP请求工具类
 * 
 * @date 2017年10月13日 下午3:00:49
 * @author tonasun
 */
public class HttpRequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

	/**
	 * get请求url,参数在方法外拼接好，headers中放请求头中需要添加的参数
	 */
	public static String getStringInvoke(String url, Map<String, String> headers) throws IOException {
		logger.info("begin to http invoke[GET], url is [{}]", url);
		CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
		logger.debug("http client hash [{}]", httpClient.hashCode());
		httpClient.start();
		HttpGet httpGet = new HttpGet(url);
		/*
		 * httpGet.addHeader("Content-Type", "text/json;charset=UTF-8");
		 * httpGet.addHeader("Connection", "close");// 表示是否需要持久连接。
		 */
		if (headers != null && headers.size() > 0) {
			for (Entry<String, String> header : headers.entrySet()) {
				httpGet.addHeader(header.getKey(), header.getValue());
			}
		}
		Future<HttpResponse> future = httpClient.execute(httpGet, null);
		HttpResponse response;
		try {
			response = future.get();
			logger.debug("get response status line [{}]", response.getStatusLine());
			int statusCode = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				HttpEntity responseEntity = response.getEntity();
				String ret = EntityUtils.toString(responseEntity, "UTF-8");
				EntityUtils.consume(responseEntity);
				return ret;
			} else {
				logger.error("response entity is null");
				return null;
			}
		} catch (InterruptedException e) {
			logger.error("InterruptedException occured when http client execute...{}", e);
			return null;
		} catch (ExecutionException e) {
			logger.error("ExecutionException occured when http client execute...{}", e);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				logger.error("Thread sleep exception...{}", e);
			}
		} catch (ParseException e) {
			logger.error("ParseException occured when http client execute...{}", e);
		} catch (IOException e) {
			logger.error("IOException occured when http client execute...{}", e);
		} finally {
			if (httpGet != null) {
				httpGet.releaseConnection();
			}
			if (httpClient != null) {
				httpClient.close();
			}
			logger.debug("http client has been closed...");
		}
		return null;
	}

	/**
	 * post请求返回String
	 * 
	 * @param requestMap 请求参数
	 * @param url        请求地址
	 * @param headers    请求头信息
	 * @return
	 */
	public static String postStringInvoke(Map<String, String> requestMap, String url, Map<String, String> headers) {
		logger.info("begin invoke httpclient post to [{}]...", url);
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
		logger.debug("http client hash [{}]", httpClient.hashCode());
		httpClient.start();
		if (headers != null && headers.size() > 0) {
			for (Entry<String, String> header : headers.entrySet()) {
				httpPost.addHeader(header.getKey(), header.getValue());
			}
		}
		List<NameValuePair> lists = new ArrayList<>();
		if (requestMap != null && requestMap.size() > 0) {
			for (Entry<String, String> request : requestMap.entrySet()) {
				lists.add(new BasicNameValuePair(request.getKey(), request.getValue()));
			}
		}
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(lists, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("UrlEncodedFormEntity exception", e);
		}
		httpPost.setEntity(entity);
		Future<HttpResponse> future = httpClient.execute(httpPost, null);
		HttpResponse response;
		try {
			response = future.get();
			logger.debug("get response status line [{}]", response.getStatusLine());
			int statusCode = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				HttpEntity responseEntity = response.getEntity();
				String ret = EntityUtils.toString(responseEntity, "UTF-8");
				EntityUtils.consume(responseEntity);
				return ret;
			} else {
				logger.error("response entity is null");
				return null;
			}
		} catch (InterruptedException e) {
			logger.error("InterruptedException occured when http client execute...{}", e);
			return null;
		} catch (ExecutionException e) {
			logger.error("ExecutionException occured when http client execute...{}", e);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				logger.error("Thread sleep exception...{}", e);
			}
		} catch (IOException e) {
			logger.error("IOException occured when http client execute...{}", e);
			return null;
		} finally {
			try {
				httpPost.releaseConnection();
				httpClient.close();
				logger.debug("http client has been closed...");
			} catch (IOException e) {
				logger.error("IOException occured when http client close...{}", e);
			}
		}
		return null;
	}

	/**
	 * get请求,返回InputStream
	 */
	public static InputStream getStreamInvoke(String url, Map<String, String> headers) {
		logger.info("begin to http invoke[GET], url is [{}]", url);
		CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
		logger.debug("http client hash [{}]", httpClient.hashCode());
		httpClient.start();
		HttpGet httpGet = new HttpGet(url);
		/*
		 * httpGet.addHeader("Content-Type", "text/json;charset=UTF-8");
		 * httpGet.addHeader("Connection", "close");
		 */

		if (headers != null && headers.size() > 0) {
			for (Entry<String, String> header : headers.entrySet()) {
				httpGet.addHeader(header.getKey(), header.getValue());
			}
		}

		Future<HttpResponse> future = httpClient.execute(httpGet, null);
		HttpResponse response;
		try {
			response = future.get();
			logger.debug("get response status line [{}]", response.getStatusLine());
			int statusCode = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				HttpEntity responseEntity = response.getEntity();
				InputStream content = responseEntity.getContent();
				return content;
			} else {
				logger.error("response entity is null");
				return null;
			}
		} catch (InterruptedException e) {
			logger.error("InterruptedException occured when http client execute...{}", e);
			return null;
		} catch (ExecutionException e) {
			logger.error("ExecutionException occured when http client execute...{}", e);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				logger.error("Thread sleep exception...{}", e);
			}
		} catch (ParseException e) {
			logger.error("ParseException occured when http client execute...{}", e);
		} catch (IOException e) {
			logger.error("IOException occured when http client execute...{}", e);
		} finally {
			try {
				httpGet.releaseConnection();
				httpClient.close();
				logger.debug("http client has been closed...");
			} catch (IOException e) {
				logger.error("IOException occured when http client close...{}", e);
			}
		}
		return null;
	}

	/**
	 * post请求 发送加密内容适合可用此方法
	 * 
	 * @param url         请求地址
	 * @param sendData    请求内容
	 * @param urlencode   编码格式
	 * @param connTimeOut 连接超时时间
	 * @param readTimeOut 读取时间
	 * @param contentType contentType
	 * @param header      请求头信息
	 * @return
	 */
	public static String sendAndRcvHttpPostBase(String url, String sendData, String urlencode, int connTimeOut,
			int readTimeOut, String contentType, Map<String, String> header) {
		String result = "";
		BufferedReader in = null;
		DataOutputStream out = null;
		int code = 999;
		HttpsURLConnection httpsConn = null;
		HttpURLConnection httpConn = null;
		try {
			URL reqUrl = new URL(url);
			if (url.startsWith("https://")) {
				httpsConn = (HttpsURLConnection) reqUrl.openConnection();
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					}
				} };
				SSLContext sc = SSLContext.getInstance("TLS");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				httpsConn.setSSLSocketFactory(sc.getSocketFactory());
				HostnameVerifier hv = new HostnameVerifier() {
					@Override
					public boolean verify(String urlHostName, SSLSession session) {
						return true;
					}
				};
				httpsConn.setHostnameVerifier(hv);

				httpsConn.setRequestProperty("Accept-Charset", urlencode);
				httpsConn.setRequestProperty("User-Agent", "java HttpsURLConnection");
				if (header != null) {
					for (String key : header.keySet()) {
						httpsConn.setRequestProperty(key, (String) header.get(key));
					}
				}
				httpsConn.setRequestMethod("POST");
				httpsConn.setUseCaches(false);
				httpsConn.setRequestProperty("Content-Type", contentType);
				httpsConn.setConnectTimeout(connTimeOut);
				httpsConn.setReadTimeout(readTimeOut);
				httpsConn.setDoInput(true);
				httpsConn.setInstanceFollowRedirects(true);
				if (sendData != null) {
					httpsConn.setDoOutput(true);
					// 获取URLConnection对象对应的输出流
					out = new DataOutputStream(httpsConn.getOutputStream());
					// 发送请求参数
					out.write(sendData.getBytes(urlencode));
					// flush输出流的缓冲
					out.flush();
					out.close();
				}
				// 取得该连接的输入流，以读取响应内容
				in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream(), urlencode));
				code = httpsConn.getResponseCode();
			} else {
				httpConn = (HttpURLConnection) reqUrl.openConnection();
				httpConn.setRequestProperty("Accept-Charset", urlencode);
				httpConn.setRequestProperty("user-agent", "java HttpURLConnection");
				if (header != null) {
					for (String key : header.keySet()) {
						httpConn.setRequestProperty(key, (String) header.get(key));
					}
				}
				httpConn.setRequestMethod("POST");
				httpConn.setUseCaches(false);
				httpConn.setRequestProperty("Content-Type", contentType);
				httpConn.setConnectTimeout(connTimeOut);
				httpConn.setReadTimeout(readTimeOut);
				httpConn.setDoInput(true);
				httpConn.setInstanceFollowRedirects(true);
				if (sendData != null) {
					httpConn.setDoOutput(true);
					// 获取URLConnection对象对应的输出流
					out = new DataOutputStream(httpConn.getOutputStream());
					// 发送请求参数
					out.write(sendData.getBytes(urlencode));
					// flush输出流的缓冲
					out.flush();
					out.close();
				}
				// 取得该连接的输入流，以读取响应内容
				in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), urlencode));
				code = httpConn.getResponseCode();
			}
			if (HttpURLConnection.HTTP_OK == code) {
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
					logger.info("反回结果：{}", line);
				}
				if (result.length() > 2000) {
					logger.info("http返回结果:{}", result.substring(0, 2000));
				} else {
					logger.info("http返回结果:{}", result);
				}
			} else {
				result = null;
				throw new Exception("支付失败,服务端响应码：" + code);
			}
		} catch (IOException e) {
			logger.error("http通讯失败:{}", e);
			result = null;
		} catch (Exception e) {
			logger.error("http通讯失败:{}", e);
			result = null;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("DataOutputStream close exception:{}", e);
				}
			}
			if (httpConn != null) {
				httpConn.disconnect();
			}
			if (httpsConn != null) {
				httpsConn.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("BufferedReader close exception:{}", e);
				}
			}
		}
		return result;
	}
}
