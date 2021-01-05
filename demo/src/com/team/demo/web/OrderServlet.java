package com.team.demo.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.demo.dao.DetailOrderDAO;
import com.team.demo.dao.OrderDAO;
import com.team.demo.util.DBHelper;
import com.team.demo.util.Utils;

@WebServlet("/order.s")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO dao = new OrderDAO();
	private DetailOrderDAO ddao = new DetailOrderDAO();
	private String name;
	private int uid;

	/**
	 * 查询用户uid 订购商品
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	public void queryByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession();
		name = (String) session.getAttribute("loginedAccount");
		String sql = "select uid from cake_user where uname= ?";
		List<Map<String, Object>> ret = DBHelper.selectListMap(sql,name);
		for (Map<String, Object> map : ret) {
			uid = (int) map.get("uid");
		}
		System.out.println("用户名字：" + name);
		System.out.println("用户ID：" + uid);
		write(response, name);
	}

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
		System.out.println(cid);
		String cnum = request.getParameter("num");
		String price = request.getParameter("price");
		if(uid>0) {
		int did = 0;
		int j = dao.addAll(uid, Double.parseDouble(cnum) * Double.parseDouble(price));
		List<Map<String, Object>> list1 = (List<Map<String, Object>>) dao.queryDid(uid);
		Map<String, Object> map2 = list1.get(0);
		did = (int) map2.get("max(did)");
		int i = dao.addOrder(uid, Integer.parseInt(cid), Integer.parseInt(cnum), price, did);
		if (i > 0 && j > 0) {
			write(response, "1");
		} else {
			write(response, "0");
		}
		}else {
			write(response, "-1");
		}
	}

	/**
	 * 立即购买
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	public void buyNow(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String sname = request.getParameter("sname");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		int did = 0;
		int oid = 0;
		if(uid>0) {
		List<Map<String, Object>> list1 = (List<Map<String, Object>>) dao.queryDid(uid);
		Map<String, Object> map = list1.get(0);
		did = (int) map.get("max(did)");
		List<Map<String, Object>> list2 = (List<Map<String, Object>>) dao.queryOid(uid);
		Map<String, Object> map1 = list2.get(0);
		oid = (int) map1.get("max(oid)");
		System.out.println("++++" + did);
		System.out.println("++++" + oid);
		int i = dao.addLupdate(tel, Utils.getNowTime(), addr, sname, did);
		int j = dao.deleteOrder(oid);
		if(i>0 && j>0) {
		write(response, "1");
		}else {
			write(response, "0");
		}
	}else {
		write(response, "-1");
	}

	}


	public void addCartOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String cid = request.getParameter("cid");
		System.out.println(cid);
		String cnum = request.getParameter("num");
		
		String price = request.getParameter("price");
		if(uid>0) {
		int i = dao.addCartOrder(uid, Integer.parseInt(cid), Integer.parseInt(cnum), price);
		if (i > 0) {
			write(response, "1");
		} else {
			write(response, "0");
		}
		}else {
			write(response, "-1");
		}

	}

	String total;
	String[] userBuyData;
	String[] cnum;

	public void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		total = request.getParameter("totalMoney");
		userBuyData = request.getParameterValues("userBuyData[]");
		cnum = request.getParameterValues("cnum[]");
		System.out.println(total);
		System.out.println(userBuyData);
		System.out.println(cnum);
		if(uid>0) {
		int k = dao.add(uid);
		List<Map<String, Object>> list1 = (List<Map<String, Object>>) dao.queryDid(uid);
		System.out.println(dao.queryDid(uid));
		Map<String, Object> map2 = list1.get(0);
		int did = (int) map2.get("max(did)");

		int g = dao.updateCartOrder(did, uid);
		if (k > 0 && g > 0) {
			System.out.println("添加订单成功！");
			int oid = 0;
			int num = 0;
			for (int i = 0; i < userBuyData.length; i++) {

				oid = Integer.parseInt(userBuyData[i].toString());
				num = Integer.parseInt(cnum[i].toString());
				int j = dao.updateOrder(num, oid);

				if (j > 0) {
					write(response, "1");
					System.out.println("修改成功");
				} else {
					write(response, "0");
					System.out.println("修改失败");
				}
				write(response, "1");
			}
		}
		}else {
			write(response, "-1");
		}

	}

	public void buy(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String sname = request.getParameter("sname");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		int did = 0;
		int oid = 0;
		int j = 0;
		int k = 0;
		if(uid>0) {
		for (int i = 0; i < userBuyData.length; i++) {
			List<Map<String, Object>> list2 = (List<Map<String, Object>>) dao.queryDid(uid);
			Map<String, Object> map2 = list2.get(0);
			did = (int) map2.get("max(did)");
			oid = Integer.parseInt(userBuyData[i].toString());
			System.out.println("++++" + did);
			System.out.println("++++" + oid);
			j = dao.addupdate(tel, Utils.getNowTime(), addr, Double.parseDouble(total), sname, did);
			k = dao.deleteOrder(oid);
			System.out.println("++++++++++++" + total);
			if (j > 0 && k > 0) {
				write(response, total);
				System.out.println("购买成功！");
			} else {
				write(response, "0");
				System.out.println("购买失败！");
			}

		}
		}else {
			write(response, "-1");
		}

	}

	public void queryOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		if(uid>0) {
		write(response, dao.queryOrder(uid));
		}else {
			write(response, "-1");
		}
	}

	public void queryDetail(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		if(uid>0) {
		List<Map<String, Object>> list1 = (List<Map<String, Object>>) dao.queryDid(uid);
	
		Map<String, Object> map2 = list1.get(0);
		int did = (int) map2.get("max(did)");
		System.out.println(did);
		write(response, ddao.queryDetailOrder(did));
		}else {
			write(response, "-1");
		}
	}

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

	public void deleteCarts(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int oid = 0;
		String deleteData[] = request.getParameterValues("deleteData[]");
		if(deleteData!=null && deleteData.length!=0) {
		for (int i = 0; i < deleteData.length; i++) {
			oid = Integer.parseInt(deleteData[i].toString());
			write(response, dao.deleteOrder(oid));
		}
		}else {
			write(response, "-1");
		}
	}

	

	public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

		/**
		 * 数组传参 ?oid=1&oid=2&... var p = new URLSearchParams p.append(oid,1);
		 * 
		 */
		request.getParameterValues("oid");
	}

}
