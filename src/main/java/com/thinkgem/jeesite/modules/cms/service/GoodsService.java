/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.OrderDetail;
import com.thinkgem.jeesite.modules.cms.dao.GoodsDao;

/**
 * 商品Service
 * @author wharlookingfor
 * @version 2013-05-01
 */
@Component
@Transactional(readOnly = true)
public class GoodsService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(GoodsService.class);
	
	@Autowired
	private GoodsDao goodsDao;
	
	public Goods get(Long id) {
		return goodsDao.findOne(id);
	}
	
	public Page<Goods> find(Page<Goods> page, Goods goods) {
		DetachedCriteria dc = goodsDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(goods.getName())){
			dc.add(Restrictions.like("name", "%"+goods.getName()+"%"));
		}
		if (StringUtils.isNotEmpty(goods.getCode())){
			dc.add(Restrictions.like("code", "%"+goods.getCode()+"%"));
		}
		dc.add(Restrictions.eq("delFlag", Goods.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return goodsDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Goods goods) {
		goods.setUpdate_date(new Date());
		goodsDao.save(goods);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		goodsDao.deleteById(id);
	}
	

	
	
}
