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
import com.thinkgem.jeesite.modules.cms.entity.Receivable;

/**
 * 应收DAO接口
 * @author wharlookingfor
 * @version 2013-05-12
 */
public interface ReceivableDao extends ReceivableDaoCustom, CrudRepository<Receivable, Long> {

	@Modifying
	@Query("update Receivable set del_flag='" + Receivable.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface ReceivableDaoCustom extends BaseDao<Receivable> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class ReceivableDaoImpl extends BaseDaoImpl<Receivable> implements ReceivableDaoCustom {

}
