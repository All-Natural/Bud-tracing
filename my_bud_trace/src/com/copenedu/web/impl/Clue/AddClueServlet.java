package com.copenedu.web.impl.Clue;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copenedu.services.impl.Clue.ManageClueServicesImpl;
import com.copenedu.web.support.BaseServlet;

@WebServlet("/add_clue.htm")
public class AddClueServlet extends BaseServlet {

	/**
	 * 添加线索
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String toPath="/add_clue.jsp";
		//1.获取页面组件
		Map<String, String> dto=this.parseRequest(request);
		//2.实例化IMPL对象
		ManageClueServicesImpl servicesImpl=new ManageClueServicesImpl(dto);
		//3.执行结果
		int key =servicesImpl.addClue();
		String msg = "信息已保存,请提交相关图片。";
		int infoFlag = 1;
		request.setAttribute("msg", msg);
		request.setAttribute("flag", infoFlag);
		request.setAttribute("key", key);
		return toPath;
	}

}
