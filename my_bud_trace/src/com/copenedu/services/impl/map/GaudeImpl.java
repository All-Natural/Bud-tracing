package com.copenedu.services.impl.map;

import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class GaudeImpl extends JdbcServicesSupport
{

	public GaudeImpl(Map<String, String> dto) 
	{
		super(dto);
	}
	
	@Deprecated
	public Map<String, String> querySid() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select y.gaosid")
				.append("  from gaude y")
				.append(" where y.gaouid = ?")
				;
		
		Object[] params = {
				getInteger("uid")
		};
		
		return queryForMap(builder.toString(), params);
	}
	
	public Map<String, String> queryGaoSid() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select y.usid")
				.append("  from user y")
				.append(" where y.uid = ?")
				;
		
		Object[] params = {
				getInteger("uid")
		};
		
		return queryForMap(builder.toString(), params);
	}
	
	public Map<String, String> queryKeyword() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select y.gaokeyword")
				.append("  from gaude y")
				.append(" where y.gaouid = ?")
				;
		
		Object[] params = {
				getInteger("uid")
		};
		
		return queryForMap(builder.toString(), params);
	}
}
