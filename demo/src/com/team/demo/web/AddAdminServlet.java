package com.team.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.biz.AdminBiz;
import com.team.demo.util.Cake;
import com.team.demo.util.Admin;


/**
 * Servlet implementation class CreateSingerServlet
 */
@WebServlet("/addAdmin.s")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz=new AdminBiz();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Admin admin=new Admin();
		admin.setAname(request.getParameter("aname"));
		admin.setApwd(request.getParameter("apwd"));
		admin.setAtel(request.getParameter("atel"));
		admin.setAemail(request.getParameter("aemail"));
		admin.setStatus(request.getParameter("status"));
		adminBiz.create(admin);
		response.getWriter().append("保存成功");			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
