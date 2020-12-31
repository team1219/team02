package com.team.demo.biz;

import com.team.demo.dao.ManagerUserDao;
import com.team.demo.util.User;

/**
 * 歌手的业务类，定义增删改的业务方法
 * 
 *
 */

public class ManagerUserBiz {

	private ManagerUserDao dao=new ManagerUserDao();
	public void create(User user) {
		/**
		 * 增加蛋糕
		 */
		if(user.getUname()==null) {
				//...验证逻辑省略
			
		}
		try {
			dao.insert(user);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 删除蛋糕
	 * @param id
	 */
	public void delete(String uid) {
		
	try {
		dao.deleteById(uid);
	}catch(Exception e) {
		e.printStackTrace();
	}
		
	}
	

}
