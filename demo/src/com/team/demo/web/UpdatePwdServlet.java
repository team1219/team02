package com.team.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.biz.AdminBiz;


/**
 * Servlet implementation class Del
 */
@WebServlet("/updatePwd.s")
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  private AdminBiz ssBiz=new AdminBiz();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String apwd=request.getParameter("apwd");
		String aid=request.getParameter("aid");
		System.out.println("apwd"+apwd);
		System.out.println("aid"+aid);
		ssBiz.updatePwdById(apwd,aid);
		response.getWriter().append("重置管理员密码成功");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
