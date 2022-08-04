package com.copenedu.web.impl.Manager.Admin;

import com.copenedu.services.impl.Manager.AdminManagementServicesImpl;
import com.copenedu.web.support.BaseServlet;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add_admin.htm")
public class AddAdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 添加审核管理员
	 */
	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/add_admin.jsp";
		Map<String,String> dto = this.parseRequest(request);
		AdminManagementServicesImpl sImpl=new AdminManagementServicesImpl(dto);
		String msg = sImpl.addAdmin()?"添加成功!":"添加失败";
		request.setAttribute("msg", msg);
		return toPath;
	}
}
