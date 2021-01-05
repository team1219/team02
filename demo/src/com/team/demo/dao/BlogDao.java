package com.team.demo.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.team.demo.util.DBHelper;

public class BlogDao {
	public List<?> queryTid(){
		String sql = "select * from topic";
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 商品详情
	 * @param cid
	 * @return
	 */
	public List<?> queryCid(int cid){
		String sql = "select * from cake where cid = " + cid;
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 头部商品显示
	 * @return
	 */
	public List<?> queryLimit(){
		int index = ThreadLocalRandom.current().nextInt(0, 78 + 1);
		String sql = "select * from cake limit " + index + ",1";
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 点击发表后查询蛋糕
	 * @param tid
	 * @return
	 */
	public List<?> queryCake(int tid){
		String sql = "select * from topic where tid=" + tid;
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 话题渲染
	 * @param did
	 * @return
	 */
	public List<?> queryDiscuss(int tid){
		String sql = "select did,topic_discuss.uid,dcontent,dtime,uname,head " +
				"from topic_discuss,cake_user,topic where topic_discuss.uid=cake_user.uid"
				+ " and topic.tid=topic_discuss.tid and topic.tid= "+tid;
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 删除评论
	 * @param did
	 * @return
	 */
	public int deleteTopic(int did){
		String sql = "delete from topic_discuss where did = ? ";
		try {
			return DBHelper.update(sql,did);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return did;
	}
	/**
	 * 新增评论
	 * @param did
	 * @return
	 */
	public void insertTopic(String dcontent,int uid,String tid) throws SQLException{
		String sql = "insert into topic_discuss values(null,?,now(),?,?)";
		DBHelper.update(sql, dcontent,uid,tid);
	}
}
