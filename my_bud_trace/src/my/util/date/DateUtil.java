package my.util.date;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DateUtil {
	//使用DateUtil定义的格式
		static SimpleDateFormat sdf_dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		static SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
		static SimpleDateFormat sdf_t = new SimpleDateFormat("HH:mm:ss");
		static SimpleDateFormat ymd1 = new SimpleDateFormat("YYYY/MM/dd");//指定
		static SimpleDateFormat ymd2 = new SimpleDateFormat("YYYY-MM-dd");
		static SimpleDateFormat ymd3 = new SimpleDateFormat("YYYY MM dd");
		public static SimpleDateFormat ymd4  = new SimpleDateFormat("yyyy/MM/dd");
		static SimpleDateFormat md1 = new SimpleDateFormat("MM/dd");
		static SimpleDateFormat md2 = new SimpleDateFormat("MM-dd");
		static SimpleDateFormat md3 = new SimpleDateFormat("MM dd");
		static SimpleDateFormat hm1 = new SimpleDateFormat("HH:mm");
		static SimpleDateFormat hm2 = new SimpleDateFormat("HH mm");
		static SimpleDateFormat hms1 = new SimpleDateFormat("HH:mm:ss");//指定
		static SimpleDateFormat hms2 = new SimpleDateFormat("HH mm ss");
		static HashMap<String, Object> map = new HashMap();
		 
		static {
				Class claz = null;
				try {
					claz = Class.forName("my.util.date.DateUtil");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				Field[] fields = claz.getDeclaredFields();
				for(Field field : fields)
				{
						if(Modifier.isStatic(field.getModifiers()))
						{
								String name = field.getName();
								Object obj = null;
								try {
									obj = field.get(null);
								} catch (IllegalArgumentException | IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								map.put(name, obj);
						}
				}
				
		}
		
		public static void init()
		{
				
		}
		
		public static String date2Str(Date date)
		{
				return sdf_dt.format(date);
		}
		
		public static String date2Str(Date date, SimpleDateFormat format)
		{
				return format.format(date);
		}
		
//		public static String parseAnd2Str(String source, String pattern) throws ParseException
//		{
//				Date date = sdf_dt.parse(source);
////				Simp
//		}
		
//		public static String date2Str(Date date)
		
		public static void main(String[] args)
		{
				init();
				System.out.println("hello");
		}

}
