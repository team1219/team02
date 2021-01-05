package com.team.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team.demo.util.CakeOrder;
import com.team.demo.util.DBHelper;
import com.team.demo.util.DBHelper.ResultSetMapper;


public class CakeOrderDao {
	//实体对象属性映射对象
	private CakeOrderMapper cakeOrderMapper=new CakeOrderMapper();
	public List<CakeOrder> selectPage(int page) throws SQLException{
		//计算开始页数
		int begin=(page-1)*10;
		//mysql分页查询语法 ：limit
		String sql="select * from cake_order limit ?,10";
		return DBHelper.selectList(sql,cakeOrderMapper,begin);
	}
	public int selectCount() {
		String sql="select count(*) cnt from cake_order";
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
}
class CakeOrderMapper implements ResultSetMapper<CakeOrder> {
	public CakeOrder map(ResultSet rs){
		CakeOrder cakeOrder=new CakeOrder();
		try {

			cakeOrder.setOid(rs.getInt("oid"));
			cakeOrder.setCnum(rs.getString("cnum"));
			cakeOrder.setUid(rs.getInt("uid"));
			cakeOrder.setCid(rs.getInt("cid"));
			cakeOrder.setDid(rs.getInt("did"));
			cakeOrder.setPrice(rs.getString("price"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cakeOrder;
	}
	
}
