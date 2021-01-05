package com.team.demo.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.dao.OrderDAO;
import com.team.demo.util.DBHelper;

@WebServlet("/order.s")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO dao = new OrderDAO();

	/**
	 * 查询所以用户信息 必须提供请求和响应对象参数
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String cid = request.getParameter("cid");
		String cnum = request.getParameter("num");
		String name = request.getParameter("name");
		System.out.println(name);
		String sql = "select uid from cake_user where uname = ?";
		List<Map<String, Object>> ret = DBHelper.selectListMap(sql,name);
		for(Map<String, Object> map:ret) {
			System.out.println(map);
			int uid = (int) map.get("uid");
			System.out.println("用户ID:" + uid);
			int i = dao.addOrder(uid, Integer.parseInt(cid), Integer.parseInt(cnum));
			if (i > 0) {
				write(response, "1");
			} else {
				write(response, "0");
			}
		}
		
	}

	public void queryOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String name = request.getParameter("name");
		System.out.println(name);
		String sql = "select uid from cake_user where uname =? ";
		List<Map<String, Object>> ret = DBHelper.selectListMap(sql,name);
		int uid=0;
		for(Map<String, Object> map:ret) {
			System.out.println(map);
			uid = (int) map.get("uid");		
		}
		System.out.println("用户ID:" + uid);
		write(response, dao.query(uid));
		
	}
	
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String oid =request.getParameter("oid");
		int i = dao.deleteOrder(Integer.parseInt(oid));
		if(i>0) {
			write(response, "1");
			System.out.println("移出成功");
		}else {
			write(response, "0");
			System.out.println("移出失败");
		}
	}
<<<<<<< HEAD

	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String deleteData[] = request.getParameterValues("deleteData[]");
		int oid = 0;
		if(deleteData!=null && deleteData.length!=0) {
		for (int i = 0; i < deleteData.length; i++) {
			oid = Integer.parseInt(deleteData[i].toString());
			write(response, dao.deleteOrder(oid));
		}
		}else {
			write(response, "-1");
		}
	}

	

=======
>>>>>>> branch 'main' of https://github.com/team1219/team02.git
	public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String oid =request.getParameter("oid");
		String num = request.getParameter("cnum");
		int i = dao.updateOrder(Integer.parseInt(num),Integer.parseInt(oid));
		if(i>0) {
			write(response, "1");
			System.out.println("修改成功");
		}else {
			write(response, "0");
			System.out.println("修改失败");
		}
	}

}
