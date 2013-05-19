/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseDaoImpl;
import com.thinkgem.jeesite.modules.cms.entity.Balance;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 欠款DAO接口
 * @author wharlookingfor
 * @version 2013-05-18
 */
public interface BalanceDao extends BalanceDaoCustom, CrudRepository<Balance, Long> {

	@Modifying
	@Query("update Balance set delFlag='" + Balance.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);

//	@Query("select b.consumer, from Balance b, Receivable r, Collecting c,Consumer u  where c in elements (r.categoryList) and r in elements (u.roleList)" +
//			" and c.delFlag='" + Category.DEL_FLAG_NORMAL + "' and r.delFlag='" + Role.DEL_FLAG_NORMAL + 
//			"' and u.delFlag='" + User.DEL_FLAG_NORMAL + "' and u.id=?1 or (c.user.id=?1 and c.delFlag='" + Category.DEL_FLAG_NORMAL +
//			"') order by c.site.id, c.sort")
//	
//	public org.springframework.data.domain.Page<Balance> findAll(String name,
//			String code, Pageable springPage);
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface BalanceDaoCustom extends BaseDao<Balance> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class BalanceDaoImpl extends BaseDaoImpl<Balance> implements BalanceDaoCustom {

}
