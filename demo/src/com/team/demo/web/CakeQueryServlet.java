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
		write(response, cdao.query());
	}

}
