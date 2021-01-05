package com.team.demo.biz;

import com.team.demo.dao.AdminDao;
import com.team.demo.util.Admin;

/**
 * 管理员的业务类，定义增删改的业务方法
 */

public class AdminBiz {
	private AdminDao dao=new AdminDao();
	/**
	 * 增加用户
	 */
	public void create(Admin admin) {
		if(admin.getAname()==null) {
				//...验证逻辑省略
		}
		try {
			dao.insert(admin);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 修改管理员密码
	 */
public void updatePwdById(String apwd,String aid) {
		
		try {
			dao.updatePwdById(apwd,aid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateStatus(String aid) {
		try {
			dao.updateStatus(aid);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

}
