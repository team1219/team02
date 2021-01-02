package com.team.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.biz.DetailBiz;


/**
 * Servlet implementation class Del
 */
@WebServlet("/delDetail.s")
public class DelDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  private DetailBiz delBiz=new DetailBiz();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		int did=Integer.parseInt(request.getParameter("did"));
		delBiz.delete(did);
		response.getWriter().append("删除成功");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
