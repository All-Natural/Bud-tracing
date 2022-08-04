package com.copenedu.web.impl.Manager.Admin;

import com.copenedu.services.impl.Manager.AdminManagementServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/del_admin.htm")
public class DelAdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/show_admin_list.htm";
		Map<String, String> dto=this.parseRequest(request);
		AdminManagementServicesImpl sImpl=new AdminManagementServicesImpl(dto);
		String msg = sImpl.delAdminBatch()?"删除成功":"删除失败";
		request.setAttribute("msg", msg);
		return toPath;
	}
	

}
