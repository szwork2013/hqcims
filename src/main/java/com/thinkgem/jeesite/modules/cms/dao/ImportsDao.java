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
import com.thinkgem.jeesite.modules.cms.entity.Imports;

/**
 * 进货管理DAO接口
 * @author wharlookingfor
 * @version 2013-06-01
 */
public interface ImportsDao extends ImportsDaoCustom, CrudRepository<Imports, Long> {

	@Modifying
	@Query("update Imports set delFlag='" + Imports.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface ImportsDaoCustom extends BaseDao<Imports> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class ImportsDaoImpl extends BaseDaoImpl<Imports> implements ImportsDaoCustom {

}
