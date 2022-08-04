/**
 * 
 */
package task.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xianzhikun
 *
 */
public class MutiTaskDemo
{

		/**
		 * @param args
		 */
		public static void main(String[] args)
		{
				ExecutorService service = Executors.newFixedThreadPool(10);
				
				for(int i=0; i<20; i++)
				{
						service.execute(() -> {
							
								System.out.println("execute " + Thread.currentThread().getId());
							
						});
				}
				
		}

}
