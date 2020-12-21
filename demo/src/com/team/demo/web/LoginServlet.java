package com.team.demo.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login.s")
public class LoginServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// 获取账号
		String account = request.getParameter("account");
		// 获取密码
		String pwd = request.getParameter("pwd");
		// 获取请求中的验证码
		String rvcode = request.getParameter("vcode");
		// 获取会话中的验证码
		String svcode = (String) request.getSession().getAttribute("vcode");
		// 获取会话中的验证码时间值
		Date svtime = (Date) request.getSession().getAttribute("vtime");

		// 计算时间差(毫秒)
		long time = System.currentTimeMillis() - svtime.getTime();
		// 判断是否超时
		if (time / 1000 > 60) {
			write(response, "-2");
			return;
		}
		if (svcode.equalsIgnoreCase(rvcode) == false) {
			write(response, "-1");
			return;
		}

		System.out.println("===="+account);
		System.out.println("===="+pwd);
		if ("zhangsan".equals(account) && "123".equals(pwd)) {
			// 登录成功!
			// 获取会话对象
			HttpSession session = request.getSession();
			// 存入当前登录的对象名
			session.setAttribute("loginedAccount", account);

			// 将账号保存到cookie中
			Cookie cookie = new Cookie("loginedAccount", account);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			write(response, "1");
		} else {
			// 登录失败
			write(response, "0");
		}
	}

}
