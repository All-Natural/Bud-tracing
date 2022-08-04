package com.copenedu.services.impl.map;

import java.util.HashMap;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

/**
 * 此类不放在servlet
 * 做系统线程用，开启后不关闭
 * 注意要方法体传入参数，dto失效！！
 * @author Not a Literary Gaint
 *
 */
public class SysPromptImpl extends JdbcServicesSupport
{

	public SysPromptImpl(Map<String, String> dto) 
	{
		super(dto);
	}
	
	
	/**
	 * 需要参数
	 * grade,type, content
	 * digest自动生成
	 * 
	 * uids 
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public boolean addPrompt(Map<String, Object> data, Integer... uids) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("insert into prompt")
				.append("			(prograde, procontent, prodigest, procreate, protype, prouid, prodelflag)")
				.append("	  values(?, ?, ?, current_timestamp, ?, ?, 1)")
				;
		
		String content = (String) data.get("content");
		String digest = (content.length() > 100) ? content.substring(0, 100) : content;		//前100个字做摘要
 		
		Object[] params = {
			data.get("grade"),
			content,
			digest,
			data.get("type"),
		};
		
		return this.batchUpdate(builder.toString(), params, uids);
	}
	
	public static void main(String[] args)
	{
		SysPromptImpl impl = new SysPromptImpl(null);
		
		Integer[] uid = {4, 6 };
		Map<String, Object> data = new HashMap<>();
		data.put("content", "hello asdgsdasydasdfauygdiuqwbiducgasjkcbaksubdasiugduyasgdkuascuuasfcjyasgbkdjsankdhaskuhdasdaksjbkjasbkjddbasiudbasiuhdiuas");
		data.put("grade", 1);
		data.put("type", 2);
		
		try {
			System.out.println(impl.addPrompt(data, uid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
