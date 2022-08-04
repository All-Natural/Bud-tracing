package com.copenedu.web.impl.SySMessage;

import com.copenedu.services.impl.SySMessage.SySMessageServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifyNotice
 */
@WebServlet("/show_sys_msg.htm")
public class ShowSySMessageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath = "/show_sys_msg.jsp";
		Map<String, String> dto = this.parseRequest(request);
		SySMessageServicesImpl sImpl = new SySMessageServicesImpl(dto);
		Map<String, String> ins = sImpl.findById();
		if(ins!=null)
		{
			request.setAttribute("ins", ins);
		}
		return toPath;
	}
}
