package email;

import org.apache.commons.mail.EmailException;

public class EmailTest
{

	public static void main(String[] args)
	{
		try
		{
			MyEmailHost.Send("text", "hello world", "969216419@qq.com");
		} catch (EmailException e)
		{
			e.printStackTrace();
		}
	}
	
}
