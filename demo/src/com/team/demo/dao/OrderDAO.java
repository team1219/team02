package com.team.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.DBHelper;

public class OrderDAO {

	public int  addOrder(int uid,int cid,int cnum,String price,int did) throws SQLException {
		String sql = "insert into cake_order(uid,cid,cnum,price,did) values(?,?,?,?,?)";
		return DBHelper.update(sql, uid,cid,cnum,price,did);
	}
	
	
	public int  addCart(int oid,int uid,String cid,String cnum,String price,int did) throws SQLException {
		String sql = "insert into cake_Cart(oid,uid,cid,u_cnum,price,did) values(?,?,?,?,?,?)";
		return DBHelper.update(sql,oid, uid,cid,cnum,price,did);
	}
	
	public int  addAll(int uid,Double ptotal) throws SQLException {
		String sql = "insert into d_order(uid,ptotal) values(?,?)";
		return DBHelper.update(sql, uid,ptotal);
	}
	public int  addLupdate(String tel,String pretime,String addr,String sname,int did) throws SQLException {
		String sql = "update d_order set tel= ?,pretime= ? , addr=?,sname=? where did= ?";
		return DBHelper.update(sql,tel,pretime,addr,sname,did);
	}
	
	
	
	public int  addCartOrder(int uid,int cid,int cnum,String price) throws SQLException {
		String sql = "insert into cake_order(uid,cid,cnum,price,did) values(?,?,?,?,null)";
		return DBHelper.update(sql, uid,cid,cnum,price);
	}
	public int  updateCartOrder(int did,int uid) throws SQLException {
		String sql = "update cake_order set did=? where uid =? ";
		return DBHelper.update(sql,did, uid);
	}
	
	public int  add(int uid) throws SQLException {
		String sql = "insert into d_order(uid) values(?)";
		return DBHelper.update(sql, uid);
	}
	
	public int  addupdate(String tel,String pretime,String addr,double ptotal,String sname,int did) throws SQLException {
		String sql = "update d_order set tel= ?,pretime= ? , addr=? , ptotal= ?,sname=? where did= ?";
		return DBHelper.update(sql,tel,pretime,addr,ptotal,sname,did);
	}
	
	public List<?> queryDid(int uid){
		String sql = "select max(did) from d_order where uid = " + uid ;
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<?> queryOid(int uid){
		String sql = "select max(oid) from cake_order where uid =  " + uid ;
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<?> queryByOid(int oid){
		String sql = "select * from cake_order where oid = ?" ;
		try {
			return DBHelper.selectListMap(sql,oid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int  updateOrder(int cnum,int oid) throws SQLException {
		String sql = "update cake_order set cnum = "+cnum+" where oid = "+oid;
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
		String sql = "select  c.cid,oid,cnum,d.did,price,uname,cname,cimg "
				+ "	 from d_order d,cake_order o,cake_user u,cake c "
				+ "	where o.uid=u.uid and o.did=d.did and o.cid=c.cid and o.uid=? ";
		try {
			return DBHelper.selectListMap(sql,uid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<?> queryOrder(int uid){
		String sql = "select  oid,cnum,c.cid,price,uname,cname,cimg "
				+ "	 from cake_order o,cake_user u,cake c "
				+ "	where o.uid=u.uid and o.cid=c.cid and o.uid=? ";
		try {
			return DBHelper.selectListMap(sql,uid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
