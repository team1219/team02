package com.team.demo.web;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.dao.CakeDAO;


@WebServlet("/querycake.s")
public class CakeQueryServlet extends BaseServlet {
	
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CakeDAO cdao = new CakeDAO();
	
	public void query(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String index = request.getParameter("index");
		String size = request.getParameter("size");
		String typeid = request.getParameter("typeid");
		write(response, cdao.query(Integer.parseInt(typeid),Integer.parseInt(index),Integer.parseInt(size)));
	}
	
	public void queryAllBy(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String index = request.getParameter("index");
		String size = request.getParameter("size");
		String typeid = request.getParameter("typeid");
		String maxPrice = request.getParameter("maxPrice");
		String minPrice = request.getParameter("minPrice");
		String name = request.getParameter("cname");
		System.out.println(cdao.queryAllBy(typeid,Integer.parseInt(minPrice),Integer.parseInt(maxPrice),name,Integer.parseInt(index),Integer.parseInt(size)));
		write(response, cdao.queryAllBy(typeid,Integer.parseInt(minPrice),Integer.parseInt(maxPrice),name,Integer.parseInt(index),Integer.parseInt(size)));
	}
	
	public void queryCount(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String typeid = request.getParameter("typeid");
		String size = request.getParameter("size");
		int count = cdao.queryCount(Integer.parseInt(typeid));
		int totalPage = (count%Integer.parseInt(size)==0)?(count/Integer.parseInt(size)):((count/Integer.parseInt(size))+1);
		write(response, totalPage);
	}
	
	public void queryCountAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String typeid = request.getParameter("typeid");
		String size = request.getParameter("size");
		String name = request.getParameter("cname");
		int count = cdao.queryCountAll(typeid,name);
		int totalPage = (count%Integer.parseInt(size)==0)?(count/Integer.parseInt(size)):((count/Integer.parseInt(size))+1);
		write(response, totalPage);
	}

}
