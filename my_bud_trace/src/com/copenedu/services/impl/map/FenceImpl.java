package com.copenedu.services.impl.map;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

import my.util.str.StrUtil;

public class FenceImpl extends JdbcServicesSupport
{

	public FenceImpl(Map<String, String> dto)
	{
		super(dto);
	}
	
	public boolean storeFence() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("insert into ")
				.append("      fence(fdgfid, fdname, fddesc, fdforuser, fdcreater, ")
				.append("            fdtimecreate, fdtype, flng, flat, fradius)")
				.append("     values(?, ?, ?, ?, ?,")
				.append("            current_timestamp, ?, ?, ?, ?) ")
				;
		Object[] params = {
				
				getInteger("gfid"),
				getString("name"),
				getString("desc"),
				getString("cuid"),
				getString("uid"),
				getInteger("type"),
				Double.valueOf(getString("lng")),			//注： getdouble() 会损失精度
				Double.valueOf(getString("lat")),
				getInteger("radius")
		};
		
		return executeUpdate(builder.toString(), params) > 0;
	}
	/**
	 * 目标格式
	  	{
	    "data": {
	        "count": 1,
	        "results": [
	            {
	                "createtime": 1627446813979,
	                "desc": "学校",
	                "gfid": 127229,
	                "modifytime": 1627446813979,
	                "name": "学校",
	                "type": 1,						---add
	                "shape": {
	                    "center": "[106.504962,29.533155]",       ---  modify
	                    "radius": 1000
	                }
	            }
	        ]
	    },
		    "errcode": 10000,
		    "errdetail": null,
		    "errmsg": "OK"
		}
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> queryFence() throws Exception
	{
			StringBuilder builder = new StringBuilder()
					.append("select fdid, fdname, fddesc, fdtimecreate, fdtype, flng, flat, fradius")
					.append("  from fence")
					.append(" where fdforuser = ?")
					.append("   and fdcreater = ?")
					;
			
			Object[] params = {
					getInteger("cuid"),
					getInteger("uid")
			};
			
			return queryForList(builder.toString(), params);
	}
	
	@Deprecated
	public List<Map<String, String>> queryFence(Map<String, Integer> infos) throws Exception
	{
			StringBuilder builder = new StringBuilder()
					.append("select fdid, fdname, fddesc, fdtimecreate, fdtype, flng, flat, fradius")
					.append("  from fence")
					.append(" where fdforuser = ?")
					.append("   and fdcreater = ?")
					;
			
			Object[] params = {
					infos.get("cuid"),
					infos.get("uid")
			};
			
			return queryForList(builder.toString(), params);
	}
	
	
	public List<Map<String, String>> queryFence(Integer uid) throws Exception
	{
			StringBuilder builder = new StringBuilder()
					.append("  select fdid, fdname, fdcreater, fdforuser, fddesc,")
					.append("           fdtimecreate, fdtype, flng, flat, fradius")
					.append("   from fence")
					.append(" where fdforuser = ?")
					;
			
			Object[] params = {
					uid
			};
			
			return queryForList(builder.toString(), params);
	}
	
	public boolean deleteFence() throws Exception
	{
			StringBuilder builder = new StringBuilder()
					.append("delete from fence")
					.append(" where fdid = ? and fdcreater = ?")
					;
			
//			String[] gfids = StrUtil.splitStr(getString("gfids"));
//			return batchUpdate(builder.toString(), gfids);
			return executeUpdate(builder.toString(), new Object[]{getInteger("gfids"), getInteger("uid")}) > 0;
	}
	
}
