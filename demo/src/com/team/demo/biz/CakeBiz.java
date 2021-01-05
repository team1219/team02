package com.team.demo.biz;

import com.team.demo.dao.ManageCakeDao;
import com.team.demo.util.Cake;

/**
 * 歌手的业务类，定义增删改的业务方法
 * 
 *
 */

public class CakeBiz {

	private ManageCakeDao dao=new ManageCakeDao();
	public void create(Cake cake) {
		/**
		 * 增加蛋糕
		 */
		if(cake.getCname()==null) {
				//...验证逻辑省略
			
		}
		try {
			dao.insert(cake);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 删除蛋糕
	 * @param id
	 */
	public void delete(String cid) {
		
	try {
		dao.deleteById(cid);
	}catch(Exception e) {
		e.printStackTrace();
	}
		
	}
	/**
	 * 修改蛋糕
	 * @param cid
	 */
	public void update (Cake cake) {
		
		try {
			dao.updateById( cake);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		}
	

}
