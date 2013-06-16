/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.thinkgem.jeesite.modules.cms.entity.GoodsCount;
import com.thinkgem.jeesite.modules.cms.dao.GoodsDao;
import com.thinkgem.jeesite.modules.sys.utils.ToolsUtils;

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

	/** 
	  * @Title: findAll 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @return   
	  * @throws 
	  */ 
	public List<GoodsCount> findAll() {
		
		DetachedCriteria dc = goodsDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", Goods.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		List<Goods> goodList=goodsDao.find(dc);
		List<GoodsCount> result=new ArrayList<GoodsCount>();
		for(int i=0;i<goodList.size();i++){
			Goods goods=goodList.get(i);
			GoodsCount goodsCount=new GoodsCount();
			goodsCount.setId(goods.getId());
			goodsCount.setBrand(goods.getBrand());
			goodsCount.setCode(goods.getCode());
			goodsCount.setName(goods.getName());
			goodsCount.setNum(goods.getNum());
			goodsCount.setPurchase(goods.getPurchase());
			goodsCount.setRemarks(goods.getRemarks());
			goodsCount.setTotal(ToolsUtils.getFloat(goods.getNum()*goods.getPurchase()));
			result.add(goodsCount);
		}
		return result;
	}

	/** 
	  * @Title: getTotal 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @return   
	  * @throws 
	  */ 
	public Double  getTotal() {
		return goodsDao.getTotal();
	}
	

	
	
}
