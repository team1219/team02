package com.team.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.biz.ManagerUserBiz;
import com.team.demo.util.Cake;
import com.team.demo.util.User;


/**
 * Servlet implementation class CreateSingerServlet
 */
@WebServlet("/updateUser.s")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUserBiz userBiz=new ManagerUserBiz();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		User user=new User();
		user.setUid(Integer.parseInt(request.getParameter("uid")));
		user.setUname(request.getParameter("uname"));
		user.setUpass(request.getParameter("upass"));
		user.setGender(request.getParameter("gender"));
		user.setStatus(request.getParameter("ustatus"));
		userBiz.update(user);
		response.getWriter().append("保存成功");			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
