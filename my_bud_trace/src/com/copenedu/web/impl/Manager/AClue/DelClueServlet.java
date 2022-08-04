package com.copenedu.web.impl.Manager.AClue;

import com.copenedu.services.impl.Clue.*;
import com.copenedu.web.support.BaseServlet;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteClueServlet
 */
@WebServlet("/delete_clue.htm")
public class DelClueServlet extends BaseServlet 
{
	/**
	 * 删除线索
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/***.jsp";
		//1.获取页面组件
		Map<String, String> dto=this.parseRequest(request);
		//2.实例化IMPL对象
		ManageClueServicesImpl delete=new ManageClueServicesImpl(dto);
		//3.执行结果
		String msg=delete.delClue()?"删除线索成功":"删除线索失败";
		//4.保存至session
		request.setAttribute("msg", msg);
		return toPath;
	}
}
