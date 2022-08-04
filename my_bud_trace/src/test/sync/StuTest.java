package test.sync;

import java.util.Map;

public class StuTest 
{

	public static void main(String[] args) 
	{
		
		Stu st = new Stu() 
		{	
			{
				setName("xianzhikun");
				init();
			}

			@Override
			public String toname() 
			{
				return null;
			}
		};
		
		System.out.println(st.getName());
		
//		Map kAndV = {};
//		Map truthMap = { "answer" : 42 };
		recur(1);
	}
	
	public static void recur(int num)
	{
		System.out.println(num);
		recur(++num);
		
		
		
	}

}
