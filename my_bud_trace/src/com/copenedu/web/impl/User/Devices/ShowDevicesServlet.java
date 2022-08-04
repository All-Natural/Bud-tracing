package com.copenedu.web.impl.User.Devices;

import com.copenedu.services.impl.User.DevicesManagementServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show_dev.htm")
public class ShowDevicesServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取子端设备列表
	 */
	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "/device_list.jsp";
		Map<String,String> dto = this.parseRequest(request);
		DevicesManagementServicesImpl sImpl = new DevicesManagementServicesImpl(dto);
		List<Map<String, String>> rows = sImpl.showDevices();
		String msg="";
		if(rows == null)
		{
			msg = "请先注册吧";
			int flag = 1;
			request.setAttribute("flag", flag);
		}
		else if(rows.size()==0)
		{
			msg="暂无";
			int flag = 2;
			request.setAttribute("flag", flag);
		}
		else if(rows.size()>0)
		{
			request.setAttribute("rows", rows);
			String pageController = sImpl.getPageController("/show_dev.htm");
			request.setAttribute("pageController", pageController);
			int flag = 2;
			request.setAttribute("flag", flag);
		}
		request.setAttribute("msg", msg);
		return path;
	}
}
