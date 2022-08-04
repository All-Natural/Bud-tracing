package com.copenedu.web.impl.Clue;

import com.copenedu.services.impl.Clue.ManageClueServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show_my_clue_list.htm")
public class ShowMyClueListServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/show_my_clue.jsp";
		Map<String, String> dto = this.parseRequest(request);
		ManageClueServicesImpl sImpl =new ManageClueServicesImpl(dto);
		String msg="";
		List<Map<String, String>> rows = sImpl.findByUId();
		if(rows.size()>0)
		{
			msg="我的线索列表";
			String pageController=sImpl.getPageController("/show_my_clue_list.htm");
			request.setAttribute("rows", rows);
			request.setAttribute("pageController", pageController);
		}
		else
		{
			msg="您还没有提供线索，可以的话，去提供一下吧！";
		}
		request.setAttribute("msg", msg);
		return toPath;
	}
}
