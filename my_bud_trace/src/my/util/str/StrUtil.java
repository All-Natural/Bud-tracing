package my.util.str;

public class StrUtil 
{
	public static String joinStr(String... strs)
	{
		if(strs.length == 0) return null;
		StringBuilder builder = new StringBuilder()
				.append(strs[0]);
				;
		
		for(int i=1; i<strs.length; i++)
		{
			builder.append(",");
			builder.append( strs[i] );
		}
		
		return builder.toString();
	}
		
	
	public static String[] splitStr(String str)
	{
		return str.split("\\,");
	}
	
}
