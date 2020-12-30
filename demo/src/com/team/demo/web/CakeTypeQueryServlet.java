package com.team.demo.web;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.dao.CakeTypeDAO;

@WebServlet("/querycaketype.s")
public class CakeTypeQueryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    private CakeTypeDAO ctdao = new CakeTypeDAO();
	
	public void querycaketype(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println(ctdao.query());
		write(response, ctdao.query());
	}
}
