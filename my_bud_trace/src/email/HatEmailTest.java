/**
 * 
 */
package email;

import org.apache.commons.mail.EmailException;

/**
 * @author xianzhikun
 *
 */
public class HatEmailTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		HatEmailHost host = new HatEmailHost();				//注意初始化
		host.init();
		try
		{
			host.build("hello ", "this is test", "969216419@qq.com").send();
			System.out.println("发送成功");
		} catch (EmailException e)
		{
			System.out.println("发送失败");
			e.printStackTrace();
		}
		
	}

}
