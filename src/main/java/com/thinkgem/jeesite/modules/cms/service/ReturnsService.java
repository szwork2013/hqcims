/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.dao.ReturnsDao;
import com.thinkgem.jeesite.modules.cms.dao.ReturnListDao;
import com.thinkgem.jeesite.modules.cms.entity.Returns;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 退货车Service
 * @author wharlookingfor
 * @version 2013-05-31
 */
@Component
@Transactional(readOnly = true)
public class ReturnsService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ReturnsService.class);
	
	@Autowired
	private ReturnsDao returnsDao;
    @Autowired
    private ReturnListDao returnListDao;
	
	public Returns get(Long id) {
		return returnsDao.findOne(id);
	}
	
	public Page<Returns> find(Page<Returns> page, Returns returns) {
		DetachedCriteria dc = returnsDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", Returns.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return returnsDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Returns returns) {
		returnsDao.save(returns);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		returnsDao.deleteById(id);
	}

    /**
     * @Title: get
     * @author lookingfor
     * @Description: 退物车的查询
     * @return   
     * @throws
     */
    public Returns getCartByUser(Long user_id) {
        String sql="select * from cms_return where del_flag=0 and user_id="+user_id+" order by create_date desc";
        List<Returns> list=returnsDao.findBySql(sql);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }

    }



    /**
     * @Title: get
     * @author lookingfor
     * @Description: 退物车的查询
     * @return   
     * @throws
     */
    public Long getReturnsCountByUser(Long user_id) {
        Long num=returnListDao.getCountReturns(user_id);
        return num;

    }
}
