package com.team.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.DBHelper;


public class BoardDao {

	/**
	 * 查询所有的甜品类别
	 * List<?> 如果不是元素，可以使用?
	 * @return
	 */
	public List<?> query(){
		String sql = "select * from tbl_board where id between 1 and 8;";
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
