package com.copenedu.services.impl.prompt;

import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class DeletePromptImpl extends JdbcServicesSupport
{

	public DeletePromptImpl(Map<String, String> dto) {
		super(dto);
		
	}
	
	/**
	 * 设置删除标识，不是真的删除
	 * @return
	 * @throws Exception 
	 */
	public boolean delete() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("update prompt")
				.append("   set prodelflag = 2")
				.append(" where proid = ?")
				;
		
		return this.executeUpdate(builder.toString(), getInteger("proid")) > 0;
	}
	
	
	
}
