/*
 * Copyright (c) 2013.
 * whatlookingfor@gmail.com
 */

package com.thinkgem.jeesite.modules.cms.mybatis;

import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.Returns;

import java.util.HashMap;
import java.util.List;


/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author calvin
 */
@MyBatisRepository
public interface MybatisDao {
    /**
     * 根据ID查询客户
     * @param id
     * @return
     */
    public Consumer queryConsumerById(Long id);

    /**
     * 查询退货物品信息
     * @param map
     * @return
     */
	public List<Goods> findReturns(HashMap<String, Object> map);

    /**
     * 查询退货物品信息的总数量
     * @param map
     * @return
     */
	public int getCountReturns(HashMap<String, Object> map);

    public int getCountReturnsByUser(Long user_id);

    public Returns getReturnsByUser(Long user_id);
	
}
