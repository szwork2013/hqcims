/*
 * Copyright (c) 2013.
 * whatlookingfor@gmail.com
 */

/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.ImportCart;
import com.thinkgem.jeesite.modules.cms.entity.Querys;
import com.thinkgem.jeesite.modules.cms.entity.Returns;
import com.thinkgem.jeesite.modules.cms.mybatis.MybatisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * 商品Service
 * @author wharlookingfor
 * @version 2013-05-01
 */
@Component
@Transactional(readOnly = true)
public class MybatisService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MybatisService.class);
	
	@Autowired
	private MybatisDao dao;

    /**
     * 查询客户
     * @param id
     * @return
     */
    public Consumer queryConsumerById(Long id){
        return dao.queryConsumerById(id);
    }

    /**
     *
     * 查询退货信息
     * @param page
     * @param querys
     * @return
     */
    public Page<Goods> findReturns(Page<Goods> page, Querys querys) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("querys",querys);
		map.put("start", page.getFirstResult());
		map.put("limit", page.getMaxResults());
        List<Goods> list = dao.findReturns(map);
        page.setList(list);
        int count = dao.getCountReturns(map);
        page.setCount(count);
        return page;
    }

    public Returns getReturnsByUser(Long user_id) {
        return dao.getReturnsByUser(user_id);
    }

    public int getReturnsCountByUser(Long user_id) {
        int num=dao.getCountReturnsByUser(user_id);
        return num;
    }

	/** 
	  * @Title: findImport 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param page
	  * @param querys
	  * @return   
	  * @throws 
	  */ 
	public Page<Goods> findImport(Page<Goods> page, Querys querys) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("querys",querys);
		map.put("start", page.getFirstResult());
		map.put("limit", page.getMaxResults());
        List<Goods> list = dao.findImports(map);
        page.setList(list);
        int count = dao.getCountImports(map);
        page.setCount(count);
        return page;
	}

	/** 
	  * @Title: getImportByUser 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param user_id
	  * @return   
	  * @throws 
	  */ 
	public ImportCart getImportByUser(Long user_id) {
		return dao.getImportByUser(user_id);
	}

	/** 
	  * @Title: getImportCountByUser 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param user_id
	  * @return   
	  * @throws 
	  */ 
	public int getImportCountByUser(Long user_id) {
		int num=dao.getImportCountByUser(user_id);
        return num;
	}

	
	

	
	
}
