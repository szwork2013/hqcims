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
import com.thinkgem.jeesite.modules.cms.entity.Consumer;

/**
 * 客户维护DAO接口
 * @author wharlookingfor
 * @version 2013-04-30
 */
public interface ConsumerDao extends ConsumerDaoCustom, CrudRepository<Consumer, Long> {

	@Modifying
	@Query("update Consumer set del_flag='" + Consumer.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface ConsumerDaoCustom extends BaseDao<Consumer> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class ConsumerDaoImpl extends BaseDaoImpl<Consumer> implements ConsumerDaoCustom {

}
