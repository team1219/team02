package com.team.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.DBHelper;
import com.team.demo.util.DBHelper.ResultSetMapper;
import com.team.demo.util.Detail;

public class DetailDao {

	//实体对象属性映射对象
		private DetailMapper detailMapper=new DetailMapper();
	public List<Detail> selectPage(int page) throws SQLException{
		//计算开始页数
		int begin=(page-1)*10;
		//mysql分页查询语法 ：limit
		String sql="select did,uid,tel,pretime,ptotal,addr from d_order limit ?,10";
		return DBHelper.selectList(sql,detailMapper,begin);
	}
	public int selectCount() {
		String sql="select count(*) cnt from(\r\n" + 
				"select did,uid,tel,pretime,ptotal,addr from d_order)a " ;
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
	
	class DetailMapper implements ResultSetMapper<Detail> {
		public Detail map(ResultSet rs){
			Detail detail=new Detail();
			try {
				detail.setDid(rs.getInt("did"));
				detail.setUid(rs.getInt("uid"));
				detail.setTel(rs.getString("tel"));
				detail.setPretime(rs.getString("pretime"));
				detail.setPtotal( rs.getString("ptotal"));
				detail.setAddr(rs.getString("addr"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return detail;
		}
		
	}
}

