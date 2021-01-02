package com.team.demo.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.demo.util.DBHelper;

@WebServlet("/admin.s")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * 查询所以用户信息 必须提供请求和响应对象参数
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String sql = "select * from admin";
		List<?> ret = DBHelper.selectListMap(sql);
		write(response, ret);
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String aname = request.getParameter("aname");
		String apwd = request.getParameter("apwd");
		String sql = "select * from admin where aname=? and apwd=?";
		List<?> ret = DBHelper.selectListMap(sql, aname, apwd);
		if (ret.isEmpty() || ret == null) {
			System.out.println("登录失败");
			// 登录失败
			write(response, "0");
		} else {
			System.out.println("登录成功");
			// 登录成功!
			// 获取会话对象
			HttpSession session = request.getSession();
			// 存入当前登录的对象名
			session.setAttribute("loginedAname", aname);

			// 将账号保存到cookie中
			Cookie cookie = new Cookie("loginedAname", aname);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			write(response, "1");
		}
		System.out.println("====" + aname);
		System.out.println("====" + apwd);
	}

	public void register(HttpServletRequest request, HttpServletResponse response) {
		String aname = request.getParameter("aname");
		String apwd = request.getParameter("apwd");
		String atel = request.getParameter("atel");
		String aemail = request.getParameter("aemail");
		String sql1 = "select aname from admin where aname=?";
		try {
			List<?> ret1 = DBHelper.selectListMap(sql1, aname);
			if (ret1 == null || ret1.isEmpty()) {
				if (aname == null || aname.isEmpty()) {
					System.out.println("用户名不能为空!");
					write(response, "-1");
					return;
				}  else {
					String sql2 = "insert into admin(aname,apwd,atel,aemail) values(?,?,?,?)";
					int ret2 = DBHelper.update(sql2, aname, apwd, atel, aemail);
					if (ret2 > 0) {
						System.out.println("注册成功!");
						write(response, "1");
					}
				}
			} else {
				System.out.println("用户名已存在!");
				write(response, "0");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
