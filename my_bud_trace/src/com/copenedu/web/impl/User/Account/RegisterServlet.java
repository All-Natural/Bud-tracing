package com.copenedu.web.impl.User.Account;

import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.User.RegisterServicesImpl;
import com.copenedu.web.support.BaseServlet;

import gaude.GaudeServices;
import httpclient.MyHttp;
import httpclient.MyHttpFactory;

import java.util.HashMap;
import java.util.Map;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IssueSearchNoticeServlet
 */
@WebServlet("/register.htm")
public class RegisterServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected String execute(HttpServletRequest request, 
//			HttpServletResponse response) throws Exception
//	{
//		String toPath="/register.jsp";
//		//1.获取页面组件
//		Map<String, String> dto = this.parseRequest(request);
//		//2.实例化IMPL对象
//		RegisterServicesImpl sImpl = new RegisterServicesImpl(dto);
//		//3.执行结果获取并保存至页面Request
//		boolean re = sImpl.addUser();
//		String msg=re?"注册成功":"注册失败";
//		request.setAttribute("msg", msg);
//		return toPath;
//	}

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String toPath="/register.jsp";
		//1.获取页面组件
		Map<String, String> dto = this.parseRequest(request);
		MyHttp client = MyHttpFactory.getMyHttpClient(request);
		String url = "https://tsapi.amap.com/v1/track/service/add";
		
		
		
		//2.实例化IMPL对象
		RegisterServicesImpl sImpl = new RegisterServicesImpl(dto);
		//3.执行结果获取并保存至页面Request
		boolean re = false;
		Integer uid = sImpl.addUser();
		if(uid != null)
		{
			Map<String, String> data = new HashMap<String, String>();
			data.put("key", GaudeServices.KEY);
			data.put("name", "u_" + uid);
			JSONObject jObj = client.postByRespJson(url, data);
			Integer errcode = jObj.getInteger("errcode");
			if(errcode != null)
			{
				JSONObject dataResp = jObj.getJSONObject("data");
				Integer sid = dataResp.getInteger("sid");
				re = sImpl.updateSid(uid, sid);
			}
		}
		String msg=re?"注册成功":"注册失败";
		
		request.setAttribute("msg", msg);
		return toPath;
	}
}
