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
		String sql="SELECT did,\n" + 
				"typename, \n" + 
				"	cname, \n" + 
				"	p.pid, \n" + 
				"	pretime, \n" + 
				"	gettime, \n" + 
				"	cnum, \n" + 
				"	d.cprice, \n" + 
				"	tel, \n" + 
				"	uname, \n" + 
				"	addr \n" + 
				" \n" + 
				"FROM \n" + 
				"	cake c, \n" + 
				"	caketype t, \n" + 
				"	preced p, \n" + 
				"	predetail d, \n" + 
				"	user u \n" + 
				"WHERE \n" + 
				"	c.typeid = t.typeid \n" + 
				"AND d.pid = p.pid \n" + 
				"AND d.cid = c.cid \n" + 
				"AND u.uid = p.pid limit ?,10";
		return DBHelper.selectList(sql,detailMapper,begin);
	}
	public int selectCount() {
		String sql="select count(*) cnt from (\n" + 
				"SELECT \n" + 
				"did,typename,\n" + 
				"	cname,\n" + 
				"	p.pid,\n" + 
				"	pretime,\n" + 
				"	gettime,\n" + 
				"	cnum,\n" + 
				"	d.cprice,\n" + 
				"	tel,\n" + 
				"	uname,\n" + 
				"	addr\n" + 
				"\n" + 
				"FROM\n" + 
				"	cake c,\n" + 
				"	caketype t,\n" + 
				"	preced p,\n" + 
				"	predetail d,\n" + 
				"	user u\n" + 
				"WHERE\n" + 
				"	c.typeid = t.typeid\n" + 
				"AND d.pid = p.pid\n" + 
				"AND d.cid = c.cid\n" + 
				"AND u.uid = p.pid) a \n" ;
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
	public void insertPreced(Detail detail) throws SQLException {
		DBHelper db=new DBHelper();
		Object uid=selectByName(detail.getUname());
		String sql="insert into preced (uid,tel,pretime,gettime,ptotal,addr) "
				+ "values(?,?,?,?,?,?)"; 
		db.update(sql, uid,detail.getTel(),detail.getPretime(),detail.getGettime(),
				detail.getCnum(),detail.getAddr());
	}
	public void insertPredetail(Detail detail) throws SQLException {
		DBHelper db=new DBHelper();
		Integer pid=selectPid(detail);
		Integer cid=selectByCname(detail.getCname());
		String sql="insert into predetail(pid,cid,cnum,cprice)  "
				+ "values(?,?,?,?)"; 
		db.update(sql,pid,cid,detail.getCnum(),detail.getCprice());
		
	}
	public void deleteById(Integer did) throws SQLException {
		DBHelper db=new DBHelper();
		String sql="delete from predetail where did=?";
		db.update(sql, did);
	}
	public Object selectByName(String uname) throws SQLException {
		DBHelper dbh=new DBHelper();
		String sql="(select uid from user where uname=?) a";
		 return dbh.selectValue(sql, uname);
	
	}
	public int selectByCname(String cname) throws SQLException {
		DBHelper dbh=new DBHelper();
		String sql="(select cid from cake where cname=?) b";
		 return (int)dbh.selectValue(sql, cname);
	
	}
	public int selectPid(Detail detail) throws SQLException {
		DBHelper db=new DBHelper();
		String sql="select pid from preced where 1=1  ";//将需要添加的字符串复制到数组中
		List<Object>params=null;
		if(null!=detail){
				Object uid=selectByName(detail.getUname());
				if(null!=uid){
					sql+=" and uid =? ";
					params.add(uid);
				}
				if(null!=detail.getTel()){
					sql+=" and tel =? ";
					params.add(detail.getTel());
				}
				if(null!=detail.getPretime()){
					sql+=" and pretime =? ";
					params.add(detail.getPretime());
				}
				if(null!=detail.getGettime()){
					sql+=" and gettime =? ";
					params.add(detail.getGettime());
				}
				if(null!=detail.getCnum()){
					sql+=" and ptotal =? ";
					params.add(detail.getCnum());
				}
				if(null!=detail.getAddr()){
					sql+=" and addr =? ";
					params.add(detail.getAddr());
				}
		}
		return (int) db.selectValue(sql, params);
	}
}
	
	class DetailMapper implements ResultSetMapper<Detail> {
		public Detail map(ResultSet rs){
			Detail detail=new Detail();
			try {
				detail.setDid(rs.getInt("did"));
				detail.setTypename(rs.getString("typename"));
				detail.setCname(rs.getString("cname"));
				detail.setPid(rs.getInt("pid"));
				detail.setPretime(rs.getString("pretime"));
				detail.setGettime(rs.getString("gettime"));
				detail.setCnum(rs.getString("cnum"));
				detail.setCprice(rs.getString("cprice"));
				detail.setTel(rs.getString("tel"));
				detail.setUname(rs.getString("uname"));
				detail.setAddr(rs.getString("addr"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return detail;
		}
		
	}

