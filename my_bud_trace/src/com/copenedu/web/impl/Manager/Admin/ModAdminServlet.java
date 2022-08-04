package com.copenedu.web.impl.Manager.Admin;

import com.copenedu.services.impl.Manager.AdminManagementServicesImpl;
import com.copenedu.web.support.BaseServlet;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mod_admin.htm")
public class ModAdminServlet extends BaseServlet 
{
	private static final long serialVersionUID = 1L;
	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/show_admin_list.htm";
		String msg=null;
		Map<String, String> dto=this.parseRequest(request);
		AdminManagementServicesImpl sImpl=new AdminManagementServicesImpl(dto);
		msg=sImpl.modAdmin()?"操作成功":"操作失败";
		request.setAttribute("msg", msg);
		return toPath;
	}
}
