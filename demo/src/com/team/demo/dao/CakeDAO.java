package com.team.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.DBHelper;


public class CakeDAO {

	/**
	 * 查询所有的分类
	 * List<?> 如果不是元素，可以使用?
	 * @return
	 */
	public List<?> query(){
		String sql = "select cname,cprice,cimg from cake where typeid=2 limit 0,8";
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
