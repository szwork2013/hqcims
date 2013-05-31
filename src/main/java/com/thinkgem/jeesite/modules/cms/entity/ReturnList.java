/*
 * Copyright (c) 2013.
 * whatlookingfor@gmail.com
 */

/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.entity;

import com.thinkgem.jeesite.common.persistence.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 购物车Entity
 * @author wharlookingfor
 * @version 2013-05-03
 */
@Entity
@Table(name = "cms_cart_detail")
public class ReturnList extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Long id; 		// 编号
	private Goods goods;//产品
	private float sale; 	// 本次价格
	private int num=1; // 数量
	private Returns returns;

	public ReturnList() {

	}

	public ReturnList(Long id){
		this();
		this.id = id;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cms_cartList")
	//@SequenceGenerator(name = "seq_cms_cartList", sequenceName = "seq_cms_cartList")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="return_id")
	@NotNull
	public Returns getReturns() {
		return returns;
	}

	public void setReturns(Returns returns) {
		this.returns = returns;
	}

	@OneToOne(cascade = {CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="goods_id")
	@NotNull
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@NotNull
	public float getSale() {
		return sale;
	}

	public void setSale(float sale) {
		this.sale = sale;
	}

	@NotNull
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}




	
}


