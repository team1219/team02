package com.team.demo.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.demo.util.DBHelper;

@WebServlet("/user.s")
public class UserServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 注意：不能重写doGet 和 doPost
	 */
	
	/**
	 * 查询所以用户信息
	 * 必须提供请求和响应对象参数
	 * @param request
	 * @param response
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void query(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		String sql = "select * from tbl_user";
		List<?> ret = DBHelper.selectListMap(sql);
		write(response, ret);
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		String uname = request.getParameter("uName");
		String upass = request.getParameter("uPass");
		String sql = "select * from tbl_user where uname=? and upass=?";
		List<?> ret = DBHelper.selectListMap(sql, uname,upass);
		if(ret.isEmpty() || ret == null) {
			System.out.println("登录失败");
			write(response, ret);
		}else {
			System.out.println("登录成功");
			HttpSession session = request.getSession();
			//存入当前登录的对象名
			session.setAttribute("logineduname", uname);
			
			//将账号保存到cookie中
			Cookie cookie = new Cookie("logineduname",uname);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			write(response, ret);
		}
	}
	
	public void register(HttpServletRequest request,HttpServletResponse response) {
		String uname = request.getParameter("uName");
		String upass = request.getParameter("uPass");
		String upass1 = request.getParameter("uPass1");
		String gender = request.getParameter("gender");
		String head = request.getParameter("head");	
		String sql1 = "select uname from tbl_user where uname=?";
		try {
			List<?> ret1 = DBHelper.selectListMap(sql1,uname);
			if(ret1 == null || ret1.isEmpty()) {
				if(uname == null || uname.isEmpty()) {
					System.out.println("用户名不能为空!");
					return ;
				}else if(!upass.equals(upass1)) {
					System.out.println("密码不相同!");
					return ;
				}else {
					String sql2 = "insert into tbl_user(uname,upass,head,gender) values(?,?,?,?)";
					int ret2 = DBHelper.update(sql2, uname,upass,head,Integer.parseInt(gender));
					if(ret2>0) {
						System.out.println("注册成功!");
						write(response, ret2);
					}
				}
			}else {
				System.out.println("用户名已存在!");
				write(response, ret1);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
