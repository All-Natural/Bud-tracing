package my.init;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import task.part.PointJugdeTask;
import task.part.RemindEmailTask;
import task.part.SysPromptTask;
import task.pool.MutiPointTask;

/**
 * Servlet Filter implementation class TaskInitter
 */
@WebFilter("/TaskInitter")
public class TaskInitter implements Filter {

    /**
     * Default constructor. 
     */
    public TaskInitter() {
        
    	
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		chain.doFilter(request, response);
	}

	/**
	 * 初始化系统
	 */
		public void init(FilterConfig fConfig) throws ServletException 
		{
	//		PointJugdeTask.task.start();
				RemindEmailTask.task.start();
				SysPromptTask.task.start();
				MutiPointTask.task.start();
		}

}
