package com.copenedu.services.impl.Clue;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class ClueListImpl extends JdbcServicesSupport
{

	public ClueListImpl(Map<String, String> dto) {
		super(dto);
	}
	
	public List<Map<String, String>> getList() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select * from clue limit 10")
				;
		
		return this.queryForList(builder.toString(), new Object[0]);
	}
	
}
