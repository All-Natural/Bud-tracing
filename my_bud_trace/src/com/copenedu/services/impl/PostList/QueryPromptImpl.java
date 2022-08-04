package com.copenedu.services.impl.PostList;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class QueryPromptImpl extends JdbcServicesSupport
{

	public QueryPromptImpl(Map<String, String> dto) {
		super(dto);
	}
	
	public List<Map<String, String>> querySysPrompt() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select *")
				.append("  from prompt")
				;
		
		return this.queryForList(builder.toString(), new Object[] {});
	}
	
}
