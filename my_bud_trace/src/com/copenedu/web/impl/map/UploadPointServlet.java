package com.copenedu.web.impl.map;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.UploadPointImpl;

import cache.map.PointBuffer;
import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;
import task.part.PointJugdeTask;
import task.pool.MutiPointTask;

@WebServlet("/map/upload_point")
public class UploadPointServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
				System.out.println("point recv<<" + jreq);
		JSONObject point = jreq.getJSONObject("point");
		if( !isLegal(point) )  return new RestError(-50, "未读到经纬度数据！！");
		
		Map<String, Object> data = JsonUtil.jsonToMap(point);
		Integer uid = jreq.getInteger("uid");
		data.put("uid", uid);
		UploadPointImpl service = new UploadPointImpl(null);
		boolean isUploaded = false;
		try
		{
			isUploaded = service.upload(data);
			if(!isUploaded)
			{
				return new RestError(-30, "上传点失败！！");
			}
			else
			{
				//添加到缓存
				PointBuffer.update(uid, point);
				
				//位置判断
//				httpReq.getRequestDispatcher("/map/region_jugle").forward(httpReq, httpResp);
				//添加到线程
//				PointJugdeTask.task.addPoint(jreq);   			//添加到任务器，自行判断		此工作线程启用
				
				MutiPointTask.task.pushPoint(jreq); 				//添加到工作线程
				
				return new RestOk();
			}
		}
		catch(SQLException e)
		{
			return new RestError(-30, "数据添加失败！！");
		}
		catch(Exception e)
		{
			return new RestError(-40, "请求服务失败！！");
		}
	}
	
	public boolean isLegal(JSONObject point)
	{
			if(point.getDouble("latitude") == 0
					||point.getDouble("longitude") == 0)
			{
					return false;
			}
			return true;
	}
}
