package com.copenedu.web.impl.map;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.FenceImpl;

import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@WebServlet("/map/delete_circle_fence")
public class DeleteFenceController extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
			//�����û�
			HttpSession session = httpReq.getSession();
			Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
			if(userData == null ) return new RestError(-10, "�û�δ��¼");
			String uid = userData.get("uid");
			if(uid == null)
			{
					return new RestError(-10, "�û�δ��¼");
			}
			
			try {
					Map<String, String> map = JsonUtil.jsonToStrMap(jreq);
					map.put("uid", uid);
					
					FenceImpl service = new FenceImpl(map);
					if(service.deleteFence())
					{
						return new RestOk();
					}
					else
					{
						return new RestError(-50, "���ݲ�����,�����޷�ɾ��");
					}
			}
			catch(SQLException e)
			{
					return new RestError(-30, "���ݲ�ѯʧ�ܣ���");
			}
			catch(Exception e)
			{
					return new RestError(-40, "�������ʧ�ܣ���");
			}
			
	}
	
}
