package com.copenedu.web.impl.User.Account;

import com.copenedu.services.impl.User.ShowSelfServicesImpl;
import com.copenedu.web.support.BaseServlet;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show_self.htm")
public class UserInfoServlet extends BaseServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath="/show_self.jsp";
		Map<String, String> dto=this.parseRequest(request);
		String msg="先注册属于自己的账号吧";
		ShowSelfServicesImpl sImpl=new ShowSelfServicesImpl(dto);
		Map<String, String> ins =sImpl.showSelf();
		if(ins!=null)
		{
			request.setAttribute("ins", ins);
			msg="您的信息会在这里呈现...";
		}
		request.setAttribute("msg", msg);
		return toPath;
	}
}
