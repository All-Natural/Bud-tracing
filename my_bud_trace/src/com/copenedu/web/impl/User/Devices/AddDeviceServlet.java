package com.copenedu.web.impl.User.Devices;

import com.copenedu.services.impl.User.DevicesManagementServicesImpl;
import com.copenedu.web.support.BaseServlet;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add_dev.htm")
public class AddDeviceServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 添加子端设备
	 */
	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "/add_device.jsp";
		Map<String,String> dto = this.parseRequest(request);
		DevicesManagementServicesImpl sImpl = new DevicesManagementServicesImpl(dto);
		String msg = sImpl.addDevices()?"添加成功":"添加失败";
		request.setAttribute("msg", msg);
		return path;
	}
}
