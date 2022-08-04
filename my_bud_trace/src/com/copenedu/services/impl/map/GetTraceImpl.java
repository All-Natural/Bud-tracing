package com.copenedu.services.impl.map;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class GetTraceImpl extends JdbcServicesSupport
{

	public GetTraceImpl(Map<String, String> dto) 
	{
		super(dto);
	}
	
	public List<Map<String, String>> getTrace() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select x.posilng, x.posilat, x.positime")
				.append("  from position x")
				.append(" where x.posiuid = ?")
				.append("   and x.positime >= ?")
				.append("   and x.positime <= ?");
		
		Object[] params = {
			getInteger("uid"),
			getString("lower"),
			getString("upper")
		};
		
		return queryForList(builder.toString(), params);
	}
	
}
