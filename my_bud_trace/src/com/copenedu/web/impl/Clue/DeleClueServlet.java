package com.copenedu.web.impl.Clue;

import com.copenedu.services.impl.Clue.ManageClueServicesImpl;
import com.copenedu.web.support.BaseServlet;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleClueServlet
 */
@WebServlet("/del_my_clue.htm")
public class DeleClueServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/show_my_cluede.jsp";
		Map<String, String>dto=this.parseRequest(request);
		ManageClueServicesImpl sImpl=new ManageClueServicesImpl(dto);
		String msg=sImpl.delClue()?"操作成功":"操作失败";
		request.setAttribute("msg", msg);
		return toPath;
	}
	
}
