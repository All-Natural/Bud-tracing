package com.copenedu.services.impl.map;

import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class StoreFenceImpl extends JdbcServicesSupport
{

	public StoreFenceImpl(Map<String, String> dto)
	{
		super(dto);
	}
	
	public boolean storeFence() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("insert into fence(fdgfid, fdname, fddesc, fdforuser, fdcreater, fdtimecreate, fdtype)")
				.append("           values(?, ?, ?, ?, ?, current_timestamp, ?)")
				;
		Object[] params = {
				
				getInteger("gfid"),
				getString("name"),
				getString("desc"),
				getString("cuid"),
				getString("puid"),
				getInteger("type")
		};
		
		return executeUpdate(builder.toString(), params) > 0;
	}
	
}
