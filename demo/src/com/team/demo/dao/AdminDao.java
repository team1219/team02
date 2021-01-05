package com.team.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.Admin;
import com.team.demo.util.DBHelper;
import com.team.demo.util.DBHelper.ResultSetMapper;


public class AdminDao {
	//实体对象属性映射对象
	private AdminMapper adminMapper=new AdminMapper();
	public List<Admin> selectPage(int page) throws SQLException{
		//计算开始页数
		int begin=(page-1)*10;
		//mysql分页查询语法 ：limit
		String sql="select * from admin limit ?,10";
		return DBHelper.selectList(sql,adminMapper,begin);
	}
	public int selectCount() {
		String sql="select count(*) cnt from admin";
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

	public void insert(Admin admin) throws SQLException {
		DBHelper db=new DBHelper();
		String sql="insert into admin (aname,apwd,atel,aemail,status) values(?,?,?,?,?)"; 
		db.update(sql, admin.getAname(),admin.getApwd(),admin.getAtel(),admin.getAemail(),admin.getStatus());
		
	}
	public void updatePwdById(String apwd,String aid) throws SQLException {
		DBHelper dbh=new DBHelper();
		String sql="update admin set apwd=? where aid=?";
		dbh.update(sql, apwd,aid);
		
	}
	public void updateStatus(String aid) throws SQLException {
		DBHelper dbh=new DBHelper();
		String sql="update admin set status=0 where aid=?";
		dbh.update(sql, aid);
		
	}
}
class AdminMapper implements ResultSetMapper<Admin> {
	public Admin map(ResultSet rs){
		Admin admin=new Admin();
		try {

			admin.setAid(rs.getInt("aid"));
			admin.setAname(rs.getString("aname"));
			admin.setApwd(rs.getString("apwd"));
			admin.setAtel(rs.getString("atel"));
			admin.setAemail(rs.getString("aemail"));
			admin.setStatus(rs.getString("status"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}
	
}
