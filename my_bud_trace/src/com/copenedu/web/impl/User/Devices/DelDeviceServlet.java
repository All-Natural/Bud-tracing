package com.copenedu.web.impl.User.Devices;

import com.copenedu.services.impl.User.DevicesManagementServicesImpl;
import com.copenedu.web.support.BaseServlet;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/del_dev.htm")
public class DelDeviceServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "/show_dev.htm";
		Map<String,String> dto = this.parseRequest(request);
		DevicesManagementServicesImpl sImpl = new DevicesManagementServicesImpl(dto);
		String msg = sImpl.delDevices()?"删除成功":"删除失败";
		request.setAttribute("msg", msg);
		return path;
	}
}
