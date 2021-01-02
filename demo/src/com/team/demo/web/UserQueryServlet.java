package com.team.demo.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team.demo.dao.ManagerUserDao;
import com.team.demo.util.User;

@WebServlet("/addUserQuery.s")
public class UserQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   private ManagerUserDao userDao=new ManagerUserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String page=request.getParameter("page");
		int ipage=page==null? 1:Integer.parseInt(page);
			try {
				List<User> rows = userDao.selectPage(ipage);
				int total = userDao.selectCount();
				Map<String,Object> data=new HashMap<>();
				data.put("rows", rows);
				data.put("total", total);
				String json=new Gson().toJson(data);
				response.getWriter().append(json);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

