/**
 * 
 */
package task.su;

import java.util.LinkedList;

/**
 * @author xianzhikun
 *
 */
public abstract class AbstractHatTask<T> extends Thread implements HatTask<T>
{
		LinkedList<T> dataQuene;
		boolean running;
		long sleepWhenNoData;
		
		public AbstractHatTask()
		{
				dataQuene = new LinkedList<T>();
				running = true;
				sleepWhenNoData = 1000;
		}
		
		public void setSleepData(long time)
		{
				this.sleepWhenNoData = time;
		}
		
		@Override
		public void run()
		{
				while(running)
				{
						T data = dataQuene.poll();
						if(data != null)
						{
								handle(data);
						}
						else
						{
								try
								{
										Thread.sleep(sleepWhenNoData);
								} catch (InterruptedException e)
								{
										e.printStackTrace();
								}
						}
				}
		}

		@Override
		public void addToQueue(T obj)
		{
				dataQuene.add(obj);
		}
		
		@Override
		public void finish()
		{
				running = false;
		}

		@Override
		public void finishWithInterrupt()
		{
				
		}
}
