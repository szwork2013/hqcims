/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.dao.*;
import com.thinkgem.jeesite.modules.cms.entity.*;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 退货车Service
 * @author wharlookingfor
 * @version 2013-05-31
 */
@Component
@Transactional(readOnly = true)
public class ImportCartService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ImportCartService.class);
	
	@Autowired
	private ImportCartDao importCartDao;
	@Autowired
	private ImportListDao importListDao;
	@Autowired
	private ImportsDao importsDao;
	@Autowired
	private GoodsDao goodsDao;
	public ImportCart get(Long id) {
		return importCartDao.findOne(id);
	}

	@Transactional(readOnly = false)
	public ImportCart save(ImportCart returns) {
		ImportCart returns1=importCartDao.save(returns);
        return returns1;
	}

	
	@Transactional(readOnly = false)
	public void deleteAll(Long id) {
		importListDao.deleteByPid(id);
		importCartDao.deleteById(id);
	}


    /**
     * @Title: get
     * @author lookingfor
     * @Description: 退物车的查询
     * @return   
     * @throws
     */
    public ImportCart getImportCartByUser(Long user_id) {
        String sql="select * from cms_import_cart where del_flag=0 and user_id="+user_id+" order by create_date desc";
        List<ImportCart> list=importCartDao.findBySql(sql);
        System.out.println(list.size());
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
    public Long getImportCountByUser(Long user_id) {
        Long num=importListDao.getCountReturns(user_id);
        return num;

    }

    public List<ImportList> findList(Long user_id) {
        String sql="select d.* from cms_import_detail d,cms_import_cart r\n" +
                   "  where r.user_id="+user_id+" and r.id=d.cart_id\n" +
                   "  and r.del_flag=0";
        List<ImportList> list=importListDao.findBySql(sql);
        return list;
    }

	/** 
	  * @Title: saveOrder 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id
	  * @param currentUser   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
    public void saveOrder(Long id, User user) {
        //第一步 根据ID从购物车查询数据
        ImportCart returns=importCartDao.findOne(id);


        List<ImportList> childList=returns.getChildList();
        for(int i=0;i<childList.size();i++){
        	ImportList cartList=childList.get(i);
            
        	Imports imports=new Imports();
        	Consumer consumer=returns.getConsumer();
        	
        	imports.setConsumer(consumer);
        	imports.setGoods(cartList.getGoods());
			imports.setUser(user);
        	imports.setNum(cartList.getNum());
        	imports.setPurchase(cartList.getPurchase());
        	imports.setRate(cartList.getRate());
        	imports.setSale(cartList.getSale());
        	
        	Goods goods=cartList.getGoods();
        	
        	int num=goods.getNum();
			goods.setNum(num+imports.getNum());
			goods.setPurchase(imports.getPurchase());
			goods.setRate(imports.getRate());
			goods.setSale(imports.getSale());
			
			importsDao.save(imports);
			goodsDao.save(goods);
        }


        //第三步 清空购物车
        importListDao.deleteByPid(id);
        importCartDao.deleteById(id);

    }

}
