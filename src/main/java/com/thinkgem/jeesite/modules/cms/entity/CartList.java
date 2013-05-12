/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 购物车Entity
 * @author wharlookingfor
 * @version 2013-05-03
 */
@Entity
@Table(name = "cms_cart_detail")
public class CartList extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Long id; 		// 编号
	private Goods goods;//产品
	private float sale; 	// 本次价格
	private int num=1; // 数量
	private Cart cart;

	public CartList() {
		
	}

	public CartList(Long id){
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
	@JoinColumn(name="cart_id")
	@NotNull
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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


