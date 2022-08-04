/**
 * 
 */
package task.su;

/**
 * @author xianzhikun
 *
 */
public interface HatTask<T>
{
	public void addToQueue(T obj);
	
	public void handle(T obj);
	
	public void finish();
	
	public void finishWithInterrupt();
}
