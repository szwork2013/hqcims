/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.dao.ImportListDao;
import com.thinkgem.jeesite.modules.cms.dao.ReturnListDao;
import com.thinkgem.jeesite.modules.cms.entity.ImportList;
import com.thinkgem.jeesite.modules.cms.entity.ReturnList;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 退货车Service
 * @author wharlookingfor
 * @version 2013-05-31
 */
@Component
@Transactional(readOnly = true)
public class ImportListService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ImportListService.class);
	
	@Autowired
	private ImportListDao returnListDao;
	
	public ImportList get(Long id) {
		return returnListDao.findOne(id);
	}

	
	@Transactional(readOnly = false)
	public void save(ImportList returnList) {
		returnListDao.save(returnList);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		returnListDao.deleteById(id);
	}
    @Transactional(readOnly = false)
    public void update(ImportList list) {
         returnListDao.update(list.getId(),list.getSale(),list.getNum(),list.getPurchase(),list.getRate());
    }
}
