package com.team.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.DBHelper;

public class OrderDAO {

	public int  addOrder(int uid,int cid,int cnum) throws SQLException {
		String sql = "insert into cake_order(uid,cid,cnum,status) values(?,?,?,false)";
		return DBHelper.update(sql, uid,cid,cnum);
	}
	
	public int  updateOrder(int cnum,int oid) throws SQLException {
		String sql = "update cake_order set cnum = "+cnum+ " where oid = "+oid;
		return DBHelper.update(sql);
	}
	public int  deleteOrder(int oid) throws SQLException {
		String sql = "delete from cake_order where oid=? ";
		return DBHelper.update(sql,oid);
	}
	
	/**
	 * 查询所有的购物车(订单表) 根据uid
	 * List<?> 如果不是元素，可以使用?
	 * @return
	 */
	public List<?> query(int uid){
		String sql = "select oid,o.cid,o.uid,uname,cname,cprice,cnum,cimg,status "
				+ "       from cake c,cake_order o,cake_user u "
				+ "          where c.cid=o.oid and o.uid=u.uid and o.uid = ? ";
		try {
			return DBHelper.selectListMap(sql,uid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
