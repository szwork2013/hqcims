/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseDaoImpl;
import com.thinkgem.jeesite.modules.cms.entity.Cart;
import com.thinkgem.jeesite.modules.cms.entity.Order;

/**
 * 购物DAO接口
 * @author wharlookingfor
 * @version 2013-05-03
 */
public interface OrderDao extends OrderDaoCustom, CrudRepository<Order, Long> {

	/** 
	  * @Title: updateTotal 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id   
	  * @throws 
	  */ 
	@Modifying
	@Query("update Order set total=(select sum(sale*num) from OrderList where order.id = ?1) where id = ?1")
	public void updateTotal(Long id);

	/** 
	  * @Title: deleteById 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id   
	  * @throws 
	  */ 
	@Modifying
	@Query("update Order set del_flag=1 where id = ?1")
	public void deleteById(Long id);


	@Modifying
	@Query("update Order set status=?2 where id = ?1")
	public void updateStatus(Long id,int status);
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface OrderDaoCustom extends BaseDao<Order> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDaoCustom {

}
