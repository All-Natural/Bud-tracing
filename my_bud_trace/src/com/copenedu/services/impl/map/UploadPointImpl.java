package com.copenedu.services.impl.map;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class UploadPointImpl extends JdbcServicesSupport
{

	public UploadPointImpl(Map<String, String> dto) 
	{
		super(dto);
	}
	
	public boolean upload(Map<String, Object> data) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("insert into position")
				.append("			(posiuid, posilng, posilat, posialt, positime, posispeed)")
				.append("	  values(?,?,?,?,?,?)")
				;
		
		//STR_TO_DATE('?','%Y-%m-%d %H:%i:%s')
		
		Object[] params = {
				data.get("uid"),
//				getDouble("longitude"),
//				getDouble("latitude"),
//				getDouble("altitude"),
//				Double.valueOf(getString("longtitude")),
//				Double.valueOf(getString("latitude")),
//				Double.valueOf(getString("altitude")),
				data.get("longitude"),
				data.get("latitude"),
				data.get("altitude"),
				data.get("time"),
				data.get("speed")
		};
		
		return this.executeUpdate(builder.toString(), params) > 0;
	}
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String timeValToStr(Long value)
	{
		return format.format(new Date(value));
	}
	
	//不损失精�?
	public Double strToDouble(String str)
	{
		return Double.valueOf(str);
	}
	
}
