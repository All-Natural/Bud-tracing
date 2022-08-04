/**
 * 
 */
package task.su;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianzhikun
 *
 */
public class TaskManager
{
		public static void runMutiTask()
		{
				
		}
		
//		HashMap<String, HatTask> 
		
		class Manager<T>
		{
				int num ;
				int index ;
				List<HatTask<T>> threads;
				
				//分发服务，采用轮训机制
				public void dispatch(T data)
				{
						if(index < num)
						{
								threads.get(index).addToQueue(data);
								index++;
						}
						else
						{
								threads.get(0).addToQueue(data);
								index = 0;
						}
				}
				
				public Manager(int num, Class claz) throws Exception
				{
						if(num <= 0) throw new Exception("不能定义小于1的线程任务！！");
						this.num = num;
						this.index = 0;
						
						Class type = null;
						try
						{
								type = claz.asSubclass(HatTask.class);
						}
						catch(Exception e)
						{
								System.out.println("初始化失败");
						}
						
						if(type == null) return;
						
						this.threads = new ArrayList<HatTask<T>>();
						for(int i=0; i<num; i++)
						{
								
						}
				}
				
				public void startMutiTask()
				{
						
				}
		}
		
		public void init()
		{
				
				
				
		}
		
		public void register(HatTask task)
		{
				
		}
}
