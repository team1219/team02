package com.team.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.Cake;
import com.team.demo.util.DBHelper;
import com.team.demo.util.DBHelper.ResultSetMapper;


public class ManageCakeDao {
	//实体对象属性映射对象
	private CakeMapper cakeMapper=new CakeMapper();
	public List<Cake> selectPage(int page) throws SQLException{
		//计算开始页数
		int begin=(page-1)*10;
		//mysql分页查询语法 ：limit
		String sql="select * from cake limit ?,10";
		return DBHelper.selectList(sql,cakeMapper,begin);
	}
	public int selectCount() {
		String sql="select count(*) cnt from cake";
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

	public void insert(Cake cake) throws SQLException {
		DBHelper db=new DBHelper();
		String sql="insert into cake (typeid,cname,cprice,ccontent,cimg,size) values(?,?,?,?,?,?)"; 
		db.update(sql, cake.getTypeid(),cake.getCname(),cake.getCprice(),cake.getContent(),cake.getCimg(),cake.getSize());
		
	}
	public void deleteById(String cid) throws SQLException {
		DBHelper dbh=new DBHelper();
		String sql="delete from cake where cid=?";
		dbh.update(sql, cid);
		
	}
}
class CakeMapper implements ResultSetMapper<Cake> {
	public Cake map(ResultSet rs){
		Cake cake=new Cake();
		try {
			cake.setCid(rs.getInt("cid"));
			cake.setTypeid(rs.getInt("typeid"));
			cake.setCname(rs.getString("cname"));
			cake.setCprice(rs.getString("cprice"));
			cake.setContent(rs.getString("ccontent"));
			cake.setCimg(rs.getString("cimg"));
			cake.setEmp(rs.getString("emp"));
			cake.setSize(rs.getString("size"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cake;
	}
	
}
