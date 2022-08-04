package com.copenedu.web.impl.Manager.Admin;

import com.copenedu.services.impl.Manager.AdminManagementServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show_admin_list.htm")
public class ShowAdminListServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 添加审核管理员
	 */
	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/show_admin_list.jsp";
		Map<String,String> dto = this.parseRequest(request);
		AdminManagementServicesImpl sImpl=new AdminManagementServicesImpl(dto);
		List<Map<String, String>> rows = sImpl.showAdminList();
		String msg = "暂无";
		if (rows.size()>0)
		{
			request.setAttribute("rows", rows);
			String pageController = sImpl.getPageController("/show_admin_list.htm");
			request.setAttribute("pageController", pageController);
			msg="";
		}
		else
		{
			msg="无相应结果";
		}
		request.setAttribute("msg", msg);
		return toPath;
	}
}
