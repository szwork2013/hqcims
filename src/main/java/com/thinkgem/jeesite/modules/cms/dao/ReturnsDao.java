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
import com.thinkgem.jeesite.modules.cms.entity.Returns;

/**
 * 退货车DAO接口
 * @author wharlookingfor
 * @version 2013-05-31
 */
public interface ReturnsDao extends ReturnsDaoCustom, CrudRepository<Returns, Long> {

	@Modifying
	@Query("update Returns set delFlag='" + Returns.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface ReturnsDaoCustom extends BaseDao<Returns> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class ReturnsDaoImpl extends BaseDaoImpl<Returns> implements ReturnsDaoCustom {

}
