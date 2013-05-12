/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseDaoImpl;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.cms.entity.OrderList;
import com.thinkgem.jeesite.modules.cms.entity.OrderDetail;

/**
 * 购物车DAO接口
 * @author wharlookingfor
 * @version 2013-05-03
 */
public interface OrderListDao extends OrderListDaoCustom, CrudRepository<OrderList, Long> {

	/** 
	  * @Title: findById 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @return   
	  * @throws 
	  */ 
	@Query("from OrderList where order.id=?1 order by id desc")
	List<OrderList> findById(Long id);

	/** 
	  * @Title: deleteById 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id   
	  * @throws 
	  */ 
	@Modifying
	@Query("delete from OrderList  where id=?1")
	void deleteById(Long id);


}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface OrderListDaoCustom extends BaseDao<OrderList> {
}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class OrderListDaoImpl extends BaseDaoImpl<OrderList> implements OrderListDaoCustom {
}
