package com.team.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.biz.DetailBiz;
import com.team.demo.util.Detail;


/**
 * Servlet implementation class CreateSingerServlet
 */
@WebServlet("/addDetail.s")
public class AddDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailBiz detailBiz=new DetailBiz();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Detail detail=new Detail();
		detail.setCname(request.getParameter("cname"));
		detail.setPretime(request.getParameter("pretime"));
		detail.setGettime(request.getParameter("gettime"));
		detail.setCnum(request.getParameter("cnum"));
		detail.setCprice(request.getParameter("cprice"));
		detail.setTel(request.getParameter("tel"));
		detail.setUname(request.getParameter("uname"));
		detail.setAddr(request.getParameter("addr"));
		detailBiz.create(detail);
		response.getWriter().append("保存成功");			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
