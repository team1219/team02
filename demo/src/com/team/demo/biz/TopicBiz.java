package com.team.demo.biz;

import java.sql.SQLException;

import com.team.demo.dao.BlogDao;
import com.team.demo.util.Utils;

public class TopicBiz {

	private BlogDao bdao = new BlogDao();
	
	public void insertTopic(String dcontent, int uid, String tid) throws BizException {
		//验证用户的输入
		Utils.checkNull(dcontent,"内容不能为空");
	
		try {
			bdao.insertTopic(dcontent, uid, tid);
		} catch (SQLException e) {
			throw new BizException("系统繁忙，请稍后再试",e);
		}
	}
}
