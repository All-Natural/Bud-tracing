package com.copenedu.web.impl.PostList.Post;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copenedu.services.impl.PostList.PostListServicesImpl;
import com.copenedu.web.support.BaseServlet;

@WebServlet("/show_com_post_list.htm")
public class ShowPostListServlet extends BaseServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath="/community.jsp";
		Map<String, String> dto = this.parseRequest(request);
		PostListServicesImpl sImpl = new PostListServicesImpl(dto);
		List<Map<String,String>> rows  =sImpl.showPostList();
		String msg = "";
		if (rows.size()>0)
		{
			request.setAttribute("rows", rows);
			String pageController = sImpl.getPageController("/show_com_post_list.htm");
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
