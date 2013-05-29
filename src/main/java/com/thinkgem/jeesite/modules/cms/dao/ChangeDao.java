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
import com.thinkgem.jeesite.modules.cms.entity.Change;

/**
 * 换货DAO接口
 * @author wharlookingfor
 * @version 2013-05-29
 */
public interface ChangeDao extends ChangeDaoCustom, CrudRepository<Change, Long> {

	@Modifying
	@Query("update Change set delFlag='" + Change.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface ChangeDaoCustom extends BaseDao<Change> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class ChangeDaoImpl extends BaseDaoImpl<Change> implements ChangeDaoCustom {

}
