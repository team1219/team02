package com.team.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.User;
import com.team.demo.util.DBHelper;
import com.team.demo.util.DBHelper.ResultSetMapper;


public class ManagerUserDao {
	//实体对象属性映射对象
	private UserMapper userMapper=new UserMapper();
	public List<User> selectPage(int page) throws SQLException{
		//计算开始页数
		int begin=(page-1)*10;
		//mysql分页查询语法 ：limit
		String sql="select * from cake_user limit ?,10";
		return DBHelper.selectList(sql,userMapper,begin);
	}
	public int selectCount() {
		String sql="select count(*) cnt from cake_user";
		try {
		List<Integer>  list=DBHelper.selectList(sql,new ResultSetMapper<Integer>() {
			
			public Integer map(ResultSet rs) {
				try {
					return rs.getInt("cnt");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
			return list.get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void insert(User user) throws SQLException {
		DBHelper db=new DBHelper();
		String sql="insert into cake_user (uname,upass,gender,ustatus) values(?,?,?,?)"; 
		db.update(sql, user.getUname(),user.getUpass(),user.getGender(),user.getStatus());
		
	}
	public void deleteById(String uid) throws SQLException {
		DBHelper dbh=new DBHelper();
		String sql="delete from cake_user where uid=?";
		dbh.update(sql, uid);
	}
	public void updateStatus(String uid) throws SQLException {
		DBHelper dbh=new DBHelper();
		String sql="update cake_user set ustatus=0 where uid=?";
		dbh.update(sql, uid);
		
	}
	public void updateById(User user) throws SQLException {
		DBHelper db=new DBHelper();
		String sql="update user set uname=?,upass=?,gender=?,ustatus=? where uid=?";
		db.update(sql,user.getUname(),user.getUpass(),user.getGender(),user.getStatus(),user.getUid());
	}
}
class UserMapper implements ResultSetMapper<User> {
	public User map(ResultSet rs){
		User user=new User();
		try {
			user.setUid(rs.getInt("uid"));
			user.setUname(rs.getString("uname"));
			user.setUpass(rs.getString("upass"));
			user.setGender(rs.getString("gender"));
			user.setHead(rs.getString("head"));
			user.setStatus(rs.getString("ustatus"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
