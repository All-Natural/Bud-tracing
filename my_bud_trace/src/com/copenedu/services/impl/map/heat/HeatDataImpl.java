package com.copenedu.services.impl.map.heat;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class HeatDataImpl extends JdbcServicesSupport
{

	public HeatDataImpl(Map<String, String> dto)
	{
		super(dto);
	}
	
	public List<Map<String, String>> heatData() throws Exception
	{
		String sql = " where";
		StringBuilder builder = new StringBuilder()
				.append("select posilng as lng, posilat as lat")
				.append("  from position")
				.append(" where positime >= ?")
				.append("   and positime <= ?")
				.append("   and posiuid = ?")
				;
		
		Object[] params = {
				getString("lower"),
				getString("upper"),
				getInteger("uid")
		};
		
		return this.queryForList(builder.toString(), params);
	}
	
}
