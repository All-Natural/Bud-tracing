package task.part;

import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.mail.EmailException;

import email.HatEmailHost;

public class RemindEmailTask extends Thread
{
	public static void main(String[] args) throws EmailException 
	{
		HatEmailHost reminder = new HatEmailHost();
		reminder.init();
		String msg = null;
		try
		{
			reminder.build("test", "test success", "969216419@qq.com").send();
		}
		catch(Exception e)
		{
			System.out.println("error happen!!");
		}
		System.out.println(msg);
	}
	
	
	public static RemindEmailTask task = new RemindEmailTask();
	HatEmailHost host = new HatEmailHost();				//注意初始化
	
	LinkedList<Map<String, String>> reminders = new LinkedList<>();
	
	private boolean runFlag;
    private long timeTry;           //没有数据的时候多久尝试上传一次
    private String url;
    
    public RemindEmailTask()
    {
    	runFlag = true;
    	timeTry = 2000;
    	host.init();
    }
	
	public void addRemindEmail(Map<String, String> email)
	{
			synchronized (this) {
					reminders.add(email);
			}
	}
    
	
	@Override
	public void run() 
	{
		while(runFlag)
		{
			Map<String, String> email = reminders.poll();
			if(email != null)
			{
				try {
					host.build(email.get("title"), email.get("content"), email.get("to"))		//构建
						.send();			//发送
				} catch (EmailException e) {
					System.out.println("发邮件失败 ！！");
					e.printStackTrace();
				}
			}
			else
			{
				try
                {
                    Thread.sleep(timeTry);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
			}
		}
	}
	
}
