package com.copenedu.web.impl.PostList.Post;

import com.copenedu.services.impl.PostList.PostListServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show_post.htm")
public class ShowPostServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath = "";
		Map<String, String> dto = this.parseRequest(request);
		PostListServicesImpl sImpl = new PostListServicesImpl(dto);
		int path=sImpl.whichPath();
		if (path==1) 
		{
			toPath="/show_my_postde.jsp";
		}
		else 
		{
			toPath="/show_post.jsp";
		}
		Map<String, String> ins = sImpl.findById();
		if(ins!=null)
		{
			request.setAttribute("ins", ins);
		}
		List<Map<String, String>> rows = sImpl.imgURL();
		if(rows.size()>0)
		{
			request.setAttribute("rows", rows);
		}
		request.setAttribute("postid", dto.get("postid"));
		return toPath;
	}
}
