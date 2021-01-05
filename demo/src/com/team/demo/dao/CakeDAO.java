package com.team.demo.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.team.demo.util.DBHelper;


public class CakeDAO {

	/**
	 * 查询所有的分类蛋糕
	 * List<?> 如果不是元素，可以使用?
	 * @return
	 */
	public List<?> query(int typeid,int index,int size){
		String sql = "select cid,cake.typeid,typename,cname,cprice,cimg from cake,caketype where "
				+ "cake.typeid=caketype.typeid and cake.typeid=? limit ?,?";
		try {
			return DBHelper.selectListMap(sql,typeid,(index-1)*size,size);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询所有的商品
	 * List<?> 如果不是元素，可以使用?
	 * @return
	 */
	public List<?> queryAllBy(String typeid,String minPrice,String maxPrice,String name,int index,int size){
		try {
		StringBuilder sql = new StringBuilder("select cid,cake.typeid,typename,cname,cprice,cimg  "
				+ "	from cake,caketype where cake.typeid=caketype.typeid ");
	        if(name != null && !name.trim().equals("") 
	        		&& typeid != null && !typeid.trim().equals("")
	        		&& minPrice != null && !minPrice.trim().equals("") 
					&& maxPrice !=null && !maxPrice.trim().equals("")) {
	        	sql.append(" and cname like ? and cake.typeid = ? and cprice between "+minPrice+" and "+maxPrice+"  limit "+(index-1)*size+","+size );
			    return DBHelper.selectListMap(sql.toString(),("%" + name + "%"),typeid);
	        }else if (typeid != null && !typeid.trim().equals("")
	        		&& minPrice != null && !minPrice.trim().equals("") 
					&& maxPrice !=null && !maxPrice.trim().equals("")) {
	            sql.append(" and cake.typeid = ?  and cprice between "+minPrice+" and "+maxPrice+" limit "+(index-1)*size+","+size);
	            return DBHelper.selectListMap(sql.toString(),typeid);
	        }else if (typeid != null && !typeid.trim().equals("")
	        		&& name != null && !name.trim().equals("")) {
	            sql.append(" and cake.typeid = ?  and cname like ? limit "+(index-1)*size+","+size );
	            return DBHelper.selectListMap(sql.toString(),typeid,("%" + name + "%"));
	        }else if (name != null && !name.trim().equals("")
	        		&& minPrice != null && !minPrice.trim().equals("") 
					&& maxPrice !=null && !maxPrice.trim().equals("")) {	   
	            sql.append(" and cname like ?  and cprice between "+minPrice+" and "+maxPrice+"  limit "+(index-1)*size+","+size);
	            return DBHelper.selectListMap(sql.toString(),("%" + name + "%"));
	        }else if (name != null && !name.trim().equals("")) {
	            sql.append(" and cname like ? limit "+(index-1)*size+","+size);
	            return DBHelper.selectListMap(sql.toString(),("%" + name + "%"));
	        }else if (typeid != null && !typeid.trim().equals("")) {
	            sql.append(" and cake.typeid = ? limit "+(index-1)*size+","+size);
	            return DBHelper.selectListMap(sql.toString(),typeid);
	        }else if(minPrice != null && !minPrice.trim().equals("") 
					&& maxPrice !=null && !maxPrice.trim().equals("")){
	        sql.append(" and cprice between "+minPrice+" and "+maxPrice+"  limit "+(index-1)*size+","+size); 
	        return DBHelper.selectListMap(sql.toString());
	        }else {
	        	sql.append(" limit "+(index-1)*size+","+size); 
		        return DBHelper.selectListMap(sql.toString());
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int queryCountAll(String typeid,String name,String minPrice,String maxPrice){
		StringBuilder sql =new StringBuilder( "select count(*) cnt from cake ");
		try {
		if(name != null && !name.trim().equals("") 
				&& typeid != null && !typeid.trim().equals("")
				&& minPrice != null && !minPrice.trim().equals("") 
				&& maxPrice !=null && !maxPrice.trim().equals("")) {
        	sql.append(" where cname like '%" + name + "%' ");
		   sql.append(" and cake.typeid = " + typeid);
		   sql.append(" and cprice between "+ minPrice +" and "+ maxPrice);
		   System.out.println(sql);
        	return DBHelper.count(sql.toString());
        }else if(name != null && !name.trim().equals("") 
				&& typeid != null && !typeid.trim().equals("")) {
        	sql.append(" where cname like '%" + name + "%' ");
		   sql.append(" and cake.typeid = " + typeid);
		   System.out.println(sql);
        	return DBHelper.count(sql.toString());
        }else if(name != null && !name.trim().equals("") 
				&& minPrice != null && !minPrice.trim().equals("") 
				&& maxPrice !=null && !maxPrice.trim().equals("")) {
        	sql.append(" where cname like '%" + name + "%' ");
		   sql.append(" and cprice between "+ minPrice +" and "+ maxPrice);
		   System.out.println(sql);
        	return DBHelper.count(sql.toString());
        }else if(typeid != null && !typeid.trim().equals("")
				&& minPrice != null && !minPrice.trim().equals("") 
				&& maxPrice !=null && !maxPrice.trim().equals("")) {
		   sql.append(" where cake.typeid = " + typeid);
		   sql.append(" and cprice between "+ minPrice +" and "+ maxPrice);
		   System.out.println(sql);
        	return DBHelper.count(sql.toString());
        }else if (typeid != null && !typeid.trim().equals("")) {
            sql.append(" where cake.typeid = " + typeid);
            System.out.println(sql);
            return DBHelper.count(sql.toString());
        }else if (name != null && !name.trim().equals("")) {	   
            sql.append(" where cname like '%" + name + "%'");
            System.out.println(sql);
            return DBHelper.count(sql.toString());
        }else if (minPrice != null && !minPrice.trim().equals("") 
        		&& maxPrice !=null && !maxPrice.trim().equals("")) {	   
            sql.append(" where cprice between "+ minPrice +" and "+ maxPrice);
            System.out.println(sql);
            return DBHelper.count(sql.toString());
        }else {
        	System.out.println(sql);
            return DBHelper.count(sql.toString());
        }
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return 0;
}

	public int queryCount(int typeid){
		String sql = "select count(*) cnt from cake where typeid="+ typeid;
		try {
			return DBHelper.count(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
