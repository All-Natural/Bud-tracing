package com.copenedu.web.impl.map;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import rest.RestError;
import rest.RestResp;
import rest.RestfulServlet;


/**
 * �ع�������Χ��������Ҫ�ϴ����ߵ·�����
 * ������ʱ��Ͷ��ʹ��
 * ���๦�ܲ鿴 StoreFenceServlet
 * @author Not a Literary Gaint
 *
 */
//@WebServlet("/map/add_circle_fence")
public class AddFenceController extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
		HttpSession session = httpReq.getSession();
		Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
		String uid = userData.get("uid");
		if(uid == null)
		{
			return new RestError(-10, "ȱ�ٲ���uid");
		}
		
		
		
		
		
		
		return null;
	}
	
	
	
}
