package com.team.demo.biz;

import com.team.demo.dao.DetailDao;
import com.team.demo.util.Cake;
import com.team.demo.util.Detail;

public class DetailBiz {

	private DetailDao dao=new DetailDao();
	public void create(Detail detail) {
		/**
		 * 增加蛋糕
		 */
		if(detail.getCname()==null) {
				//...验证逻辑省略
			
		}
		try {
			dao.insertPreced(detail);
			dao.insertPredetail(detail);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 删除蛋糕
	 * @param id
	 */
	public void delete(Integer did) {
		
	try {
		dao.deleteById(did);
	}catch(Exception e) {
		e.printStackTrace();
	}
		
	}
}
