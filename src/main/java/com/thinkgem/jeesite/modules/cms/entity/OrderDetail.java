/** 
 * @Title: OrderDetail.java 
 * @Package com.thinkgem.jeesite.modules.cms.entity 
 * @Description: (用一句话描述该文件做什么) 
 * @author lookingfor
 * @date 2013-5-2 下午9:27:05 
 * @version V1.0  
 */ 
package com.thinkgem.jeesite.modules.cms.entity;

import java.io.Serializable;


/** 
 * @ClassName: OrderDetail 
 * @Description: (这里用一句话描述这个类的作用) 
 * @author lookingfor
 * @date 2013-5-2 下午9:27:05 
 *  
 */
public class OrderDetail implements Serializable{

	/**
	 * @Fields serialVersionUID : (用一句话描述这个变量表示什么) 
	 */
	private static final long serialVersionUID = -6708536794450720793L;
	
	private int goods_id;//产品ID
	private String goods_name;//产品名称
	private String goods_code;//产品记住码
	private float purchase;//进货价格
	private float  sale;//销售价格
	private float last_sale;//上次销售价格
	private String consumer_name;//客户名称
	private String consumer_code;//客户助记码
	private Long consumer_id;
	
	
	
	
	public OrderDetail() {
		this.consumer_name = "散户";
		this.consumer_id = 1L;
	}


	public Long getConsumer_id() {
		return consumer_id;
	}


	public void setConsumer_id(Long consumer_id) {
		this.consumer_id = consumer_id;
	}


	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 * @param goods_id
	 * @param goods_name
	 * @param goods_code
	 * @param purchase
	 * @param sale
	 * @param last_sale
	 * @param consumer_name
	 * @param consumer_code 
	 */ 
	public OrderDetail(int goods_id, String goods_name, String goods_code,
			float purchase, float sale, float last_sale, String consumer_name,
			String consumer_code) {
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_code = goods_code;
		this.purchase = purchase;
		this.sale = sale;
		this.last_sale = last_sale;
		this.consumer_name = consumer_name;
		this.consumer_code = consumer_code;
	}
	
	

	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public float getPurchase() {
		return purchase;
	}
	public void setPurchase(float purchase) {
		this.purchase = purchase;
	}
	public float getSale() {
		return sale;
	}
	public void setSale(float sale) {
		this.sale = sale;
	}
	public float getLast_sale() {
		return last_sale;
	}
	public void setLast_sale(float last_sale) {
		this.last_sale = last_sale;
	}
	public String getConsumer_name() {
		return consumer_name;
	}
	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}
	public String getConsumer_code() {
		return consumer_code;
	}
	public void setConsumer_code(String consumer_code) {
		this.consumer_code = consumer_code;
	}
	@Override
	public String toString() {
		return "OrderDetail [goods_id=" + goods_id + ", goods_name="
				+ goods_name + ", goods_code=" + goods_code + ", purchase="
				+ purchase + ", sale=" + sale + ", last_sale=" + last_sale
				+ ", consumer_name=" + consumer_name + ", consumer_code="
				+ consumer_code + "]";
	}
	
	
	
	
}
