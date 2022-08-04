package httpclient;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class TestHTTP
{
	
	public static void test1()
	{
		// 此服务要求上传表单格式的参数
		String url = "https://tsapi.amap.com/v1/track/geofence/add/circle";
		
		// 要上传的参数
		List <NameValuePair> nvps = new ArrayList <>();
		nvps.add(new BasicNameValuePair("key", "21e4c0b1313e7ef2bf1647f4fdac7419"));		
		nvps.add(new BasicNameValuePair("sid", "380298"));
		nvps.add(new BasicNameValuePair("name", "test02"));		
		nvps.add(new BasicNameValuePair("center", "106.608956,29.530541"));
		nvps.add(new BasicNameValuePair("radius", "100"));		
		
		MyHttp hc = new MyHttp();
		try
		{
			System.out.println(">>" + url);
			String result = hc.post(url, nvps);
			System.out.println("<<\n" + result);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public static void test2()
	{
		// 此服务要求上传JSON格式的参数
		String url = "http://127.0.0.1:8080/demo/login.api";
		
		// 要上传的参数
		JSONObject jreq = new JSONObject();
		jreq.put("username", "邵发");
		jreq.put("password", "123456");
		
		MyHttp hc = new MyHttp();
		try
		{
			System.out.println(">>" + url);
			String result = hc.post(url, jreq.toString(2));
			System.out.println("<<\n" + result);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void test3()
	{
		// 要访问的服务
		String url = "http://127.0.0.1:8080/demo/query";
		
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
		
		// 附加参数
		List <NameValuePair> nvps = new ArrayList <>();
		nvps.add(new BasicNameValuePair("id", "20190001"));		
		nvps.add(new BasicNameValuePair("major", "火系"));	
		String query = URLEncodedUtils.format(nvps, "UTF-8");		
		url = url + "?" + query;
		
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
				System.out.println("<<\n" + content );				
			}
			
			response.close(); // 当调用close后，内部的网络连接才被关闭
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args)
	{
		test1();
		
	}

}
