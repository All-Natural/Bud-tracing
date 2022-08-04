package com.copenedu.web.impl.Clue;

import com.copenedu.services.impl.Clue.ManageClueServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show_clue_list.htm")
public class ShowClueListServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/show_clue_list.jsp";
		Map<String, String> dto=this.parseRequest(request);
		ManageClueServicesImpl CsImpl=new ManageClueServicesImpl(dto);
		List<Map<String, String>> rows=CsImpl.findByPId();
		String msg = "";
		if(rows.size()>0)
		{
			msg="相关线索如下";
			request.setAttribute("rows", rows);
			String pageController=CsImpl.getPageController("/show_clue_list.htm");
			request.setAttribute("pageController", pageController);
		}
		else
		{
			msg="暂无";
		}
		request.setAttribute("msg", msg);
		return toPath;
	}
}
