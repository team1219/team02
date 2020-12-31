package com.team.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.biz.CakeBiz;


/**
 * Servlet implementation class Del
 */
@WebServlet("/delCake.s")
public class DelCakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  private CakeBiz ssBiz=new CakeBiz();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String cid=request.getParameter("cid");
		ssBiz.delete(cid);
		response.getWriter().append("删除成功");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
