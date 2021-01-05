package com.team.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.DBHelper;

public class DetailOrderDAO {

	
	/**
	 * 查询订单表 根据did
	 * List<?> 如果不是元素，可以使用?
	 * @return
	 * @throws SQLException 
	 */
	public List<?> queryDetailOrder(int did) throws SQLException{
		
			String sql = "select  oid,cnum,d.did,price,uname,cname,cimg,ptotal  "
					+ "from d_order d,cake_order o,cake_user u,cake c" 
					+ "    where o.uid=u.uid and o.did=d.did and o.cid=c.cid and d.did = "+ did;
			return DBHelper.selectListMap(sql);
		
	}
	

}
