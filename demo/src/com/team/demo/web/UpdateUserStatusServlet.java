package com.team.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.biz.ManagerUserBiz;


/**
 * Servlet implementation class Del
 */
@WebServlet("/updateUserStatus.s")
public class UpdateUserStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  private ManagerUserBiz ssBiz=new ManagerUserBiz();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String uid=request.getParameter("uid");
		ssBiz.updateStatus(uid);
		response.getWriter().append("禁止成功");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
