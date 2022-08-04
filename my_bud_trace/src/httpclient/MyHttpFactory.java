package httpclient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyHttpFactory 
{
	
	public static MyHttp getMyHttpClient(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		MyHttp client = (MyHttp) session.getAttribute("httpclient");
		if(client == null)
		{
			client = new MyHttp();
			session.setAttribute("httpclient", client);
		}
		
		return client;
	}
	
	public static MyHttp getMyHttpClient()
	{
		MyHttp client = new MyHttp();
		return client;
	}
	
}
