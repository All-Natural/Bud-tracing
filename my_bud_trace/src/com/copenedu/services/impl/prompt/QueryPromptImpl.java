package com.copenedu.services.impl.prompt;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class QueryPromptImpl extends JdbcServicesSupport
{

	public QueryPromptImpl(Map<String, String> dto) {
		super(dto);
	}
	
	public List<Map<String, String>> querySysPrompt(String uid) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select *")
				.append("  from prompt")
				.append(" where prodelflag = 1")
				.append("   and prouid = ?")
				;
		
		return this.queryForList(builder.toString(), new Object[] {Integer.valueOf(uid)});
	}
	
	public List<Map<String, String>> queryByTypeAndGrade(Integer type, Integer grade, String uid) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select *")
				.append("  from prompt")
				.append(" where protype = ?")
				.append("   and prograde = ?")
				.append("   and prodelflag = 1")
				.append("   and prouid = ?")
				;
		
		return this.queryForList(builder.toString(), new Object[] { type, grade, Integer.valueOf(uid)});
	}
	
	public Map<String, String> queryByProid(String proid) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select *")
				.append("  from prompt")
				.append(" where prodelflag = 1")
				.append("   and proid = ?")
				;
		
		return this.queryForMap(builder.toString(), Integer.valueOf(proid));
	}
}
