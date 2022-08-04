package com.copenedu.web.impl.Manager.Admin;

import com.copenedu.services.impl.Manager.AdminManagementServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show_admin.htm")
public class ShowAdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		String toPath="/show_admin.jsp";
		Map<String, String> dto=this.parseRequest(request);
		AdminManagementServicesImpl sImpl=new AdminManagementServicesImpl(dto);
		Map<String, String> ins = sImpl.findById();
		if(ins!=null)
		{
			request.setAttribute("ins", ins);
		}
		return toPath;
	}
}
