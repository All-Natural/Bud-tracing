package com.copenedu.services.impl.map;

import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class CurrentPositionImpl extends JdbcServicesSupport
{

	public CurrentPositionImpl(Map<String, String> dto) {
		super(dto);
	}
	
	public Map<String, String> getPosition() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select * from position")
				.append(" where posiuid = ?")
				.append("   order by posiid desc limit 1")
				;
		
		Object[] params = {getInteger("uid")};
		return this.queryForMap(builder.toString(), params);
	}
	
}
