/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseDaoImpl;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.OrderDetail;

/**
 * 商品DAO接口
 * @author wharlookingfor
 * @version 2013-05-01
 */
public interface GoodsDao extends GoodsDaoCustom, CrudRepository<Goods, Long> {

	@Modifying
	@Query("update Goods set delFlag='" + Goods.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);

	
	
}

/**
 * DAO自定义接口
 * @author wharlookingfor
 */
interface GoodsDaoCustom extends BaseDao<Goods> {
	
}

//	/** 
//	  * @Title: findAllOrderDetail 
//	  * @author lookingfor
//	  * @Description: (这里用一句话描述这个方法的作用) 
//	  * @param conusmer_id
//	  * @param goods_code
//	  * @param goods_name
//	  * @param page
//	  * @return   
//	  * @throws 
//	  */ 
//	public Page<OrderList> findAllOrderDetail(int conusmer_id,
//			String goods_code, String goods_name, Page<CartList> page);
//}

/**
 * DAO自定义接口实现
 * @author wharlookingfor
 */
@Component
class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDaoCustom {

}

	/* (non-Javadoc)
	 * <p>Title: findAllOrderDetail</p> 
	 * <p>Description: </p> 
	 * @param conusmer_id
	 * @param goods_code
	 * @param goods_name
	 * @return 
	 * @see com.thinkgem.jeesite.modules.cms.dao.OrderDetailDaoCustom#findAllOrderDetail(int, java.lang.String, java.lang.String)
	 */
//	@Override
//	public Page<OrderList> findAllOrderDetail(int conusmer_id,
//			String goods_code, String goods_name,Page<OrderList> page) {
//		String sql1=" (select cg.id as goods_id,cg.name as goods_name,cg.code as goods_code," +
//							"cg.purchase as purchase ,cg.sale as sale,table1.cname as consumer_name,table1.ccode  as consumer_code"+
//				          " from cms_goods as cg,  "+
//				          " (select name as cname,code as ccode from cms_consumer where id=2) as table1) as table2 ";
//		
//		String sql2=
//				" (select * from ( "+
//				" select g.id as gid,cd.sale as last_sale from "+
//				" cms_goods g,cms_cart cc,cms_cart_detail cd "+
//				" where cc.consumer_id=2 and cc.id=cd.cart_id "+
//				" and cd.goods_id=g.id order by cc.create_date desc  "+
//				" ) as temp group by gid ) as table3 ";
//		String where_sql="";
//		if(goods_code!=null&&goods_code.length()!=0){
//			where_sql+=" where goods_code like '%"+goods_code+"%'" ;
//		}
//		if(goods_name!=null&&goods_name.length()!=0){
//			if(where_sql.length()!=0){
//				where_sql+=" and goods_name like '%"+goods_name+"%'" ;
//			}else{
//				where_sql+=" where goods_name like '%"+goods_name+"%'" ;
//			}
//		}				
//		String qlString=	" from "+sql1+" left join "+sql2 +" on table2.goods_id=table3.gid "+where_sql +" order by table2.goods_id asc";
//		
//		//String query_sql="select  new  CartList(table2.goods_id,table2.goods_name," +
//		//		"table2.purchase,table2.sale,table3.last_sale,table2.goods_code,table2.consumer_code,table2.consumer_name) ";
//		//return this.find(page, query_sql+qlString);
//		String query_sql="select goods_id,goods_name,purchase,sale,last_sale,goods_code,consumer_code,consumer_name  ";
//		// get count
//		
//
//    	if (!page.isDisabled() && !page.isNotCount()){
//	        String countQlString = "select count(*) "+qlString;  
//	        System.out.println("ssssssssss");
//	        page.setCount(4);
//			if (page.getCount() < 1) {
//				return page;
//			}
//    	}
//
//    	 javax.persistence.Query query = getEntityManager().createNativeQuery(query_sql+qlString,CartList.class);
//		// set page
//    	
//        if (!page.isDisabled()){
//	        query.setFirstResult(page.getFirstResult());
//	        query.setMaxResults(page.getMaxResults()); 
//        }
//        
//        
//        page.setList(query.getResultList());
//		return page;
//	}



