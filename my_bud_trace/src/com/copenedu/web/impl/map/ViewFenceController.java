/**
 * 
 */
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
import com.copenedu.services.impl.map.PolygonFenceImpl;

import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

/**
 * @author xianzhikun
 *
 */
@WebServlet("/map/view_fence")
public class ViewFenceController extends RestfulServlet
{
		@Override
		protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
				HashMap<String, String> queryParams) throws Exception
		{
//				//�����û�
//				HttpSession session = httpReq.getSession();
//				Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
//				if(userData == null ) return new RestError(-10, "�û�δ��¼");
//				String uid = userData.get("uid");
//				if(uid == null)
//				{
//						return new RestError(-10, "ȱ�ٲ���uid");
//				}
				
				try {
//						queryParams.put("uid", uid);
						FenceImpl circleService = new FenceImpl(queryParams);
						PolygonFenceImpl polygonService = new PolygonFenceImpl(queryParams);
						List<Map<String, String>> cfences = circleService.queryFence();
						List<Map<String, String>> pfences = polygonService.query();
						
						//��������ӵ�jsonarray
						JSONArray jsonArr = new JSONArray();
						addCircleFence(cfences, jsonArr);
						addPolygonFence(pfences, jsonArr);
						
						//�������ݸ�ʽ
						JSONObject jsonResp = new JSONObject();
						jsonResp.put("count", jsonArr.size());
						jsonResp.put("results", jsonArr);
						
						return new RestOk(jsonResp);
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
		
		
		/**
		 * ������ʽ��
		 * 
		 * 
		 * 
		�����ʽ��
		fdid, fdname, fddesc, fdtimecreate, fdtype, flng, flat, fradius
		
		Ŀ���ʽ
		  	{
		    "data": {
		        "count": 1,
		        "results": [
		            {
		                "createtime": 1627446813979,
		                "desc": "ѧУ",
		                "gfid": 127229,
		                "name": "ѧУ",
		                "type": 1,						---add
		                "points":"[[106.429524,29.536799],[106.494912,29.502655],[106.480773,29.539752],[106.478511,29.494815],[106.454962,29.528155],[106.459331,29.507425],[106.504212,29.532405]]"
		            }
		        ]
		    },
		    "errcode": 200,
		    "errdetail": null,
		    "errmsg": "OK"
			}
		 * @param fences
		 * @return
		 */
		private void addPolygonFence(List<Map<String, String>> dataList, JSONArray jsonArr)
		{
				for(Map<String, String> data : dataList)
				{
						JSONObject jobj = new JSONObject();
//						jobj.put("timecreate", data.get("potimecreate"));
						jobj.put("name", data.get("poname"));
						jobj.put("desc", data.get("podesc"));
						jobj.put("type", Integer.valueOf(data.get("potype")));
						jobj.put("gfid", Integer.valueOf(data.get("poid")));
						
						//���ͼ������
						JSONObject shape = new JSONObject();
						shape.put("style", 1);
						shape.put("points", data.get("popoints"));
						
						jobj.put("shape", shape);
						jsonArr.add(jobj);
				}
		}
		
		
		/**
		 * ������ʽ��
		 * 
		 * 
		 * 
		�����ʽ��
		fdid, fdname, fddesc, fdtimecreate, fdtype, flng, flat, fradius
		
		Ŀ���ʽ
		  	{
		    "data": {
		        "count": 1,
		        "results": [
		            {
		                "createtime": 1627446813979,
		                "desc": "ѧУ",
		                "gfid": 127229,
		                "modifytime": 1627446813979,
		                "name": "ѧУ",
		                "type": 1,						---add
		                "shape": {
		                    "center": "106.504962,29.533155",       ---  modify
		                    "radius": 1000
		                }
		            }
		        ]
		    },
		    "errcode": 10000,
		    "errdetail": null,
		    "errmsg": "OK"
			}
		 * @param fences
		 * @return
		 */
		private void addCircleFence(List<Map<String, String>> fences, JSONArray jsonArr)
		{
				for(Map<String, String> fence : fences)
				{
						JSONObject jobj = new JSONObject();
						jobj.put("name", fence.get("fdname"));
						jobj.put("desc", fence.get("fddesc"));
						jobj.put("gfid", Integer.valueOf(fence.get("fdid")));
						jobj.put("type", Integer.valueOf(fence.get("fdtype")));
						
						JSONObject shape = new JSONObject();
						shape.put("style", 0);
						shape.put("center", fence.get("flng") + "," + fence.get("flat") );
						shape.put("radius", Integer.valueOf(fence.get("fradius")));
						
						jobj.put("shape", shape);
						jsonArr.add(jobj);
				}
		}
}
