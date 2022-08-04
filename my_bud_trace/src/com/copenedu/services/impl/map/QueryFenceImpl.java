package com.copenedu.services.impl.map;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.support.JdbcServicesSupport;

@Deprecated
public class QueryFenceImpl extends JdbcServicesSupport
{

	public QueryFenceImpl(Map<String, String> dto) {
		super(dto);
	}
	
	public List<Map<String, String>> queryFence() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select x.fdgfid,x.fdtype, y.usid")
				.append("  from fence x, user y")
				.append(" where x.fdcreater = y.uid and x.fdforuser = ? and x.fdcreater = ?")
				;
		
		Object[] params = {
				getInteger("cuid"),
//				getInteger("puid")
				getInteger("uid")
		};
		return queryForList(builder.toString(), params);
	}
	
	
	public List<Map<String, String>> queryFence(JSONObject jsonObj) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select x.fdgfid,x.fdtype, x.fdcreater, x.fdforuser, x.fdname, y.usid ")
				.append("  from fence x, user y")
				.append(" where x.fdcreater =  y.uid ")
				.append("   and x.fdforuser = ?")
				;
		
		Object[] params = {
				jsonObj.getInteger("uid")
		};
		return queryForList(builder.toString(), params);
	}
	
	
//	public Map<String, String> querySid() throws Exception
//	{
//		StringBuilder builder = new StringBuilder()
//				.append("select y.gaosid")
//				.append("  from gaude y")
//				.append(" where y.gaouid = ?")
//				;
//		
//		Object[] params = {
//				getInteger("puid")
//		};
//		
//		return queryForMap(builder.toString(), params);
//	}
	
}
