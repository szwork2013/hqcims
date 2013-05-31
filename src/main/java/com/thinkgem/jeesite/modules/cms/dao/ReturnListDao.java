/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.dao;

import com.thinkgem.jeesite.modules.cms.entity.Returns;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseDaoImpl;
import com.thinkgem.jeesite.modules.cms.entity.ReturnList;

/**
 * 退货车DAO接口
 * @author wharlookingfor
 * @version 2013-05-31
 */
public interface ReturnListDao extends ReturnListDaoCustom, CrudRepository<ReturnList, Long> {

	@Modifying
	@Query("update ReturnList set delFlag='" + ReturnList.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);

    /**
     * @Title: getCountCart
     * @author lookingfor
     * @Description: (这里用一句话描述这个方法的作用)
     * @param user_id
     * @return   
     * @throws
     */
    @Query("select count(c) from ReturnList c  where c.returns.user_id=?1 and c.returns.del_flag='" + Returns.DEL_FLAG_NORMAL + "' ")
    public Long getCountReturns(Long user_id);
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface ReturnListDaoCustom extends BaseDao<ReturnList> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class ReturnListDaoImpl extends BaseDaoImpl<ReturnList> implements ReturnListDaoCustom {

}
