package com.thinkgem.jeesite.modules.cms.mybatis;

import java.util.HashMap;
import java.util.List;

import com.thinkgem.jeesite.modules.cms.entity.Balance;
import com.thinkgem.jeesite.modules.cms.entity.Balance1;


/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author calvin
 */
@MyBatisRepository
public interface BalanceMybatisDao {

	/** 
	  * @Title: queryOrderDetail 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param map
	  * @return   
	  * @throws 
	  */ 
	List<Balance1> queryBalance(HashMap<String, Object> map);

	/** 
	  * @Title: queryCountOrderDetail 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param map
	  * @return   
	  * @throws 
	  */ 
	int queryCountBalance(HashMap<String, Object> map);

	
}
