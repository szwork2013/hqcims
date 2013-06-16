/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.dao;

import com.thinkgem.jeesite.modules.cms.entity.ImportList;
import com.thinkgem.jeesite.modules.cms.entity.ReturnList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseDaoImpl;

/**
 * 退货车DAO接口
 * @author wharlookingfor
 * @version 2013-05-31
 */
public interface ImportListDao extends ImportListDaoCustom, CrudRepository<ImportList, Long> {

	@Modifying
	@Query("delete from  ImportList  where id = ?1")
	public int deleteById(Long id);

    /**
     * @Title: getCountCart
     * @author lookingfor
     * @Description: (这里用一句话描述这个方法的作用)
     * @param user_id
     * @return   
     * @throws
     */
    @Query("select count(r) from ImportList r  where r.importCart.user_id=?1 and r.importCart.del_flag='" + ReturnList.DEL_FLAG_NORMAL + "' ")
    public Long getCountReturns(Long user_id);

    @Modifying
    @Query("update  ImportList set sale=?2,num=?3,purchase=?4,rate=?5 where id = ?1")
    public int update(Long id, float sale, int num, float purchase, float rate);

    @Modifying
    @Query("delete from  ImportList  where cart_id= ?1")
    public void deleteByPid(Long id);
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface ImportListDaoCustom extends BaseDao<ImportList> {

}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class ImportListDaoImpl extends BaseDaoImpl<ImportList> implements ImportListDaoCustom {

}
