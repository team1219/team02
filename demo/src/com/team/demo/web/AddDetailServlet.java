package com.team.demo.web;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team.demo.dao.DetailDao;
import com.team.demo.util.Detail;


/**
 * Servlet implementation class CreateSingerServlet
 */
@WebServlet("/addDetail.s")
public class AddDetailServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	 
		protected void doQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			DetailDao detailDao=new DetailDao();
			response.setContentType("text/html;charset=utf-8");
			String page=request.getParameter("page");
			int ipage=page==null? 1:Integer.parseInt(page);
				try {
					List<Detail> rows = detailDao.selectPage(ipage);
					int total = detailDao.selectCount();
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
		// TODO Auto-generated method stub
		doQuery(request, response);
	}
}
