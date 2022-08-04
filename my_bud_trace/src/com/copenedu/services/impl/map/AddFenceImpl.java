package com.copenedu.services.impl.map;

import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class AddFenceImpl extends JdbcServicesSupport
{

	public AddFenceImpl(Map<String, String> dto) 
	{
		super(dto);
	}
	
	@Deprecated			
	public Map<String, String> getGaudeImfor() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("SELECT a.gaosid ")
				.append("  FROM gaude a")
				.append(" WHERE a.gaouid = ?");
		
		Object[] params = {this.getInteger("uid")};
		return queryForMap(builder.toString(), params);
	}
	
	
	public Map<String, String> getSidImfor() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("SELECT a.usid ")
				.append("  FROM user a")
				.append(" WHERE a.uid = ?");
		
		Object[] params = {this.getInteger("uid")};
		return queryForMap(builder.toString(), params);
	}
	
}
