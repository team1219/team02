package com.team.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.biz.CakeBiz;
import com.team.demo.util.Cake;


/**
 * Servlet implementation class CreateSingerServlet
 */
@WebServlet("/addCake.s")
public class AddCakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CakeBiz ssBiz=new CakeBiz();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Cake cake=new Cake();
		cake.setTypeid(Integer.parseInt(request.getParameter("typeid")));
		cake.setCname(request.getParameter("cname"));
		cake.setCprice(request.getParameter("cprice"));
		cake.setContent(request.getParameter("ccontent"));
		cake.setCimg(request.getParameter("cimg"));
	//	cake.setEmp(request.getParameter("emp"));
		cake.setSize(request.getParameter("size"));
		ssBiz.create(cake);
		response.getWriter().append("保存成功");			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
