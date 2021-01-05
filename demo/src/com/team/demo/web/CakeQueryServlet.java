package com.team.demo.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.demo.bean.Result;
import com.team.demo.biz.BizException;
import com.team.demo.biz.TopicBiz;
import com.team.demo.dao.BlogDao;
import com.team.demo.dao.CakeDAO;
import com.team.demo.util.DBHelper;


@WebServlet("/querycake.s")
public class CakeQueryServlet extends BaseServlet {
	
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CakeDAO cdao = new CakeDAO();
	private BlogDao bdao = new BlogDao();
	private TopicBiz tbiz = new TopicBiz();
	
	public void query(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String index = request.getParameter("index");
		String size = request.getParameter("size");
		String typeid = request.getParameter("typeid");
		write(response, cdao.query(Integer.parseInt(typeid),Integer.parseInt(index),Integer.parseInt(size)));
	}
	
	
	public void queryCount(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String typeid = request.getParameter("typeid");
		String size = request.getParameter("size");
		int count = cdao.queryCount(Integer.parseInt(typeid));
		int totalPage = (count%Integer.parseInt(size)==0)?(count/Integer.parseInt(size)):((count/Integer.parseInt(size))+1);
		write(response, totalPage);
	}
	
	public void queryAllBy(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String index = request.getParameter("index");
		String size = request.getParameter("size");
		String typeid = request.getParameter("typeid");
		String maxPrice = request.getParameter("maxPrice");
		String minPrice = request.getParameter("minPrice");
		String name = request.getParameter("cname");
		write(response, cdao.queryAllBy(typeid,minPrice,maxPrice,name,Integer.parseInt(index),Integer.parseInt(size)));
	}
	
	
	public void queryCountAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String typeid = request.getParameter("typeid");
		String size = request.getParameter("size");
		String name = request.getParameter("cname");
		String maxPrice = request.getParameter("maxPrice");
		String minPrice = request.getParameter("minPrice");
		
		int count = cdao.queryCountAll(typeid,name,minPrice,maxPrice);
		int totalPage = (count%Integer.parseInt(size)==0)?(count/Integer.parseInt(size)):((count/Integer.parseInt(size)+1));
		write(response, totalPage);
	}

	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void queryCid(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String cid = request.getParameter("cid");
		System.out.println(bdao.queryCid(Integer.parseInt(cid)));
		write(response,bdao.queryCid(Integer.parseInt(cid)));
	}
	/**
	 *头部商品查询
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void queryLimit(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println(bdao.queryLimit());
		write(response,bdao.queryLimit());
	}
	/**
	 * 显示到blog首页
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void queryTid(HttpServletRequest request,HttpServletResponse response) throws IOException {
		write(response,bdao.queryTid());
	}
	/**
	 * blog单独显示类别
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void queryCake(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		write(response,bdao.queryCake(Integer.parseInt(tid)));
	}
	/**
	 * 显示评论
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void queryDiscuss(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		System.out.println(bdao.queryDiscuss(Integer.parseInt(tid)));
		write(response,bdao.queryDiscuss(Integer.parseInt(tid)));
	}
	/**
	 * 删除评论
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void deleteTopic(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String did = request.getParameter("did");
		write(response,bdao.deleteTopic(Integer.parseInt(did)));
	}
	/**
	 * 新增评论
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void insertTopic(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
		String name = request.getParameter("name");
		String sql = "select uid from cake_user where uname =? ";
		List<Map<String, Object>> ret = DBHelper.selectListMap(sql,name);
		int uid=0;
		for(Map<String, Object> map:ret) {
			System.out.println(map);
			uid = (int) map.get("uid");		
		}
		System.out.println("用户ID:" + uid);
		
		String tid = request.getParameter("tid");
		String dcontent = request.getParameter("dcontent");
	
		try {
			tbiz.insertTopic(dcontent, uid,tid);
			write(response, Result.success("发帖成功！"));
		} catch (BizException e) {
			e.printStackTrace();
			write(response, Result.failure(e.getMessage()));
		}
	}
}
