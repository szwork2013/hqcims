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
import com.thinkgem.jeesite.modules.cms.entity.Cart;
import com.thinkgem.jeesite.modules.cms.entity.CartList;
import com.thinkgem.jeesite.modules.cms.entity.OrderDetail;

/**
 * 购物车DAO接口
 * @author wharlookingfor
 * @version 2013-05-03
 */
public interface CartListDao extends CartListDaoCustom, CrudRepository<CartList, Long> {

	@Modifying
	@Query("delete from  CartList where id = ?1")
	public int deleteById(Long id);

	/** 
	  * @Title: update 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id
	  * @param sale
	  * @param num   
	  * @throws 
	  */ 
	@Modifying
	@Query("update  CartList set sale=?2,num=?3 where id = ?1")
	public void update(Long id, float sale, int num);


	/** 
	  * @Title: delCartList 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id   
	  * @throws 
	  */ 
	@Modifying
	@Query("delete from CartList where cart_id=?1 ")
	public void delCartList(Long id);

	/** 
	  * @Title: findDetail 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @return   
	  * @throws 
	  */ 
	@Query("from CartList  order by id desc")
	public List<CartList> findDetail();

	/** 
	  * @Title: getCountCart 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param user_id
	  * @return   
	  * @throws 
	  */ 
	@Query("select count(c) from CartList c  where c.cart.user_id=?1 and c.cart.del_flag='" + Cart.DEL_FLAG_NORMAL + "' ")
	public Long getCountCart(Long user_id);
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface CartListDaoCustom extends BaseDao<CartList> {
}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class CartListDaoImpl extends BaseDaoImpl<CartList> implements CartListDaoCustom {
}
