package com.copenedu.web.impl.PostList.Post;

import com.copenedu.services.impl.PostList.PostListServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/issue_post.htm")
public class IssuePostServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		String toPath="/issue_post.jsp";
		//1.获取页面组件
		Map<String, String> dto=this.parseRequest(request);
		//2.实例化IMPL对象
		PostListServicesImpl sImpl=new PostListServicesImpl(dto);
		//3.执行结果获取并保存至页面Request
		int key =sImpl.addSearchPost();
		String msg = "信息已保存,请提交相关图片。";
		int infoFlag = 1;
		request.setAttribute("msg", msg);
		request.setAttribute("flag", infoFlag);
		request.setAttribute("key", key);
		return toPath;
	}
}
