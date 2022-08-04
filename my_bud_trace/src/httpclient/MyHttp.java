package httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/* 封装一个工具类
 * 
 */
public class MyHttp
{
	CloseableHttpClient httpClient;
	int timeout = 3000;	
	
	public static MyHttp ins;
	
	static
	{
		ins = new MyHttp();
	}
	
	public MyHttp()
	{
		CookieStore cookieStore = new BasicCookieStore();
		
		httpClient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();
	}
	
	
	public void close()
	{
		try
		{
			httpClient.close();
		} catch (IOException e)
		{
			
		}
	}
	
	
	public String get(String url)
	{
		// 要访问的服务
//		String url = "http://127.0.0.1:8080/demo/query";
		// 附加参数
//		List <NameValuePair> nvps = new ArrayList <>();
//		nvps.add(new BasicNameValuePair("id", "20190001"));		
//		nvps.add(new BasicNameValuePair("major", "火系"));	
//		String query = URLEncodedUtils.format(nvps, "UTF-8");		
//		url = url + "?" + query;
		
		// 创建HTTP客户端
		CloseableHttpClient httpclient;
		int timeout = 3000;
		CookieStore cookieStore = new BasicCookieStore();
		httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();
		
		// 创建HTTP请求
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout) 
				.setConnectTimeout(timeout) 
				.build();
		
		
		
		HttpGet httpget = new HttpGet(url);
		httpget.setConfig(requestConfig);
		
		// 执行HTTP请求，得到应答
		try {
			System.out.println(">>" + url);
			CloseableHttpResponse response = httpclient.execute(httpget);
			
			// 查看应答结果
			StatusLine statusLine = response.getStatusLine();
			int status = statusLine.getStatusCode();
			if (status != 200)
			{
				System.out.println("HTTP Error! Status=" + status);
			}
			else
			{
				HttpEntity dataRecv = response.getEntity();
				String content = EntityUtils.toString(dataRecv);
				return content;			
			}
			
			response.close(); // 当调用close后，内部的网络连接才被关闭
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String get(String url, Map<String, String> params)
	{
		// 附加参数
//				List <NameValuePair> nvps = new ArrayList <>();
//				nvps.add(new BasicNameValuePair("id", "20190001"));		
//				nvps.add(new BasicNameValuePair("major", "火系"));	
//				String query = URLEncodedUtils.format(nvps, "UTF-8");		
//				url = url + "?" + query;
		List <NameValuePair> nvps = new ArrayList <>();
		for(Entry<String, String> entry : params.entrySet())
		{
			BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			nvps.add(pair);
		}
		String query = URLEncodedUtils.format(nvps, "UTF-8");
		url = url + "?" + query;
		return get(url);
	}
	
	public JSONObject getByRespJson(String url, Map<String, String> params)
	{
		return JSON.parseObject(get(url, params));
	}
	
	
	public String post(String url, Map<String, String> params) throws Exception
	{
		List <NameValuePair> nvps = new ArrayList <>();
		for(Entry<String, String> entry : params.entrySet())
		{
			BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			nvps.add(pair);
		}
		return post(url, nvps);
	}
	
	
	public String post (String url, List <NameValuePair> nvps) throws Exception
	{
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout) 
				.setConnectTimeout(timeout) 
				.build();		
		
		HttpPost httppost = new HttpPost(url);			
		httppost.setConfig(requestConfig);
		
		// 表单方式, ContentType应为 "application/x-www-form-urlencoded"
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
		//entity.setContentType("application/x-www-form-urlencoded");
		httppost.setEntity(entity);			
		
		// 执行HTTP请求，得到应答
		CloseableHttpResponse response = httpClient.execute(httppost);
				
		try {			
			// 查看应答结果
			StatusLine statusLine = response.getStatusLine();
			int status = statusLine.getStatusCode();
			if (status != 200)
			{
				System.out.println("HTTP Error! Status=" + status);
			}
			else
			{
				HttpEntity dataRecv = response.getEntity();
				String msgRecv = EntityUtils.toString(dataRecv);
				return msgRecv;
			}						
		}finally
		{
			response.close(); // 当调用close后，内部的网络连接才被关闭
		}
		return null;
	}
	
	// 自定义的上传 ( 适用于 RESTful 形式的接口 )
	public String post (String url, String msgSend) throws Exception
	{
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout) 
				.setConnectTimeout(timeout) 
				.build();		
		
		HttpPost httppost = new HttpPost(url);			
		httppost.setConfig(requestConfig);
		
		// JSON / RESTful 方式, Content Type 可以为 text/plain
		StringEntity entity = new StringEntity(msgSend, Consts.UTF_8);		
		entity.setContentType("text/plain");
		httppost.setEntity(entity);	
		
		// 执行HTTP请求，得到应答
		CloseableHttpResponse response = httpClient.execute(httppost);
				
		try 
		{			
			// 查看应答结果
			StatusLine statusLine = response.getStatusLine();
			int status = statusLine.getStatusCode();
			if (status != 200)
			{
				System.out.println("HTTP Error! Status=" + status);
			}
			else
			{
				HttpEntity dataRecv = response.getEntity();
				String msgRecv = EntityUtils.toString(dataRecv);
				return msgRecv;
			}						
		}
		finally
		{
			response.close(); // 当调用close后，内部的网络连接才被关闭
		}
		return null;
	}
	
	
	public JSONObject postByRespJson(String url, String msgSend) throws Exception
	{
		String str = post(url, msgSend);
		return JSON.parseObject(str);
	}
	
	public JSONObject postByRespJson(String url, JSONObject msgsend) throws Exception
	{
		return postByRespJson(url, msgsend.toJSONString());
	}
	
	public JSONObject postByRespJson(String url, Map<String, String> msgSend) throws Exception
	{
		String str = post(url, msgSend);
		return JSON.parseObject(str);
	}
}
