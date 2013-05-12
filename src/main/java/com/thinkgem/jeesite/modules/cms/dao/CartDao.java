/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseDaoImpl;
import com.thinkgem.jeesite.modules.cms.entity.Cart;

/**
 * 购物DAO接口
 * @author wharlookingfor
 * @version 2013-05-03
 */
public interface CartDao extends CartDaoCustom, CrudRepository<Cart, Long> {

	@Modifying
	@Query("update Cart set delFlag='" + Cart.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);

	/** 
	  * @Title: update 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param cart
	  * @return   
	  * @throws 
	  */ 
	@Modifying
	@Query("update Cart set consumer_id=?2,user_id=?3 where id = ?1")
	public int update(Long id,Long consumer_id,Long user_id);


	/** 
	  * @Title: delCart 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id   
	  * @throws 
	  */ 
	@Modifying
	@Query("delete from Cart where id=?1 ")
	public void delCart(Long id);
	
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface CartDaoCustom extends BaseDao<Cart> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class CartDaoImpl extends BaseDaoImpl<Cart> implements CartDaoCustom {

}
