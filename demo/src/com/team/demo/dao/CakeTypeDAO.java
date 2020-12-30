package com.team.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.DBHelper;

public class CakeTypeDAO {

	/**
	 * 查询所有的分类蛋糕
	 * List<?> 如果不是元素，可以使用?
	 * @return
	 */
	public List<?> query(){
		String sql = "select typeid,typename,emp from caketype";
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
