package com.team.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.DBHelper;

public class BlogDao {
	public List<?> query(int tid){
		String sql = "select * from topic where tid = " + tid;
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
