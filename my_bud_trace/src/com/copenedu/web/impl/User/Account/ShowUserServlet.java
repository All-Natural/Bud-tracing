package com.copenedu.web.impl.User.Account;

import com.copenedu.services.impl.User.ShowSelfServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowUserServlet
 */
@WebServlet("/show_user.htm")
public class ShowUserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/show_user.jsp";
		Map<String, String> dto=this.parseRequest(request);
		ShowSelfServicesImpl sImpl=new ShowSelfServicesImpl(dto);
		Map<String, String> ins = sImpl.findById();
		if(ins!=null)
		{
			request.setAttribute("ins", ins);
		}
		return toPath;
	}
	
}
