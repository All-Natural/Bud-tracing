package task.part;

import java.util.LinkedList;
import java.util.Map;

import com.copenedu.services.impl.map.SysPromptImpl;

import my.entity.SysPrompt;


/**
 * 系统消息构造任务
 * @author Not a Literary Gaint
 *
 */
public class SysPromptTask extends Thread
{
	private SysPromptImpl impl = new SysPromptImpl(null);			//构造后不删除
	private LinkedList<SysPrompt> prompts =  new LinkedList<>();
	private boolean runFlag = true;
	private long timeSleep = 1000;
	
	public static SysPromptTask task = new SysPromptTask();			//创建一个全局实例用
	

	public void addSysPrompt(SysPrompt prompt)
	{
			synchronized (this) {
					prompts.add(prompt);
			}
	}


	@Override
	public void run() 
	{
		while(runFlag)
		{
			SysPrompt prompt = prompts.poll();
			if(prompt != null)
			{
				sendSystemPrompt(prompt);
			}
			else
			{
				try {
					Thread.sleep(timeSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
	
	public void sendSystemPrompt(SysPrompt prompt)
	{
			Map<String, Object> data = prompt.getData();
			Integer[] uids = prompt.getUids();
			try {
					impl.addPrompt(data, uids);
					System.out.println("发送系统消息提醒成功！！");
			} catch (Exception e) {
					System.out.println("发送系统消息提醒失败！！");
					e.printStackTrace();
			}
	}
}
