/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.entity.Imports;
import com.thinkgem.jeesite.modules.cms.dao.ImportsDao;

/**
 * 进货管理Service
 * @author wharlookingfor
 * @version 2013-06-01
 */
@Component
@Transactional(readOnly = true)
public class ImportsService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ImportsService.class);
	
	@Autowired
	private ImportsDao importsDao;
	
	public Imports get(Long id) {
		return importsDao.findOne(id);
	}
	
	public Page<Imports> find(Page<Imports> page, Imports imports) {
		DetachedCriteria dc = importsDao.createDetachedCriteria();
		if(imports.getConsumer()!=null){
            dc.createAlias("consumer", "consumer");
            if (StringUtils.isNotEmpty(imports.getConsumer().getName())){
                dc.add(Restrictions.like("consumer.name", "%"+imports.getConsumer().getName()+"%"));
            }
            if (StringUtils.isNotEmpty(imports.getConsumer().getCode())){
                dc.add(Restrictions.like("consumer.code", "%"+imports.getConsumer().getCode()+"%"));
            }
        }
        if(imports.getGoods()!=null){
            dc.createAlias("goods", "goods");
            if (StringUtils.isNotEmpty(imports.getGoods().getName())){
                dc.add(Restrictions.like("goods.name", "%"+imports.getGoods().getName()+"%"));
            }
            if (StringUtils.isNotEmpty(imports.getGoods().getCode())){
                dc.add(Restrictions.like("goods.code", "%"+imports.getGoods().getCode()+"%"));
            }
        }
		dc.add(Restrictions.eq("delFlag", Imports.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return importsDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Imports imports) {
		importsDao.save(imports);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		importsDao.deleteById(id);
	}
	
}
