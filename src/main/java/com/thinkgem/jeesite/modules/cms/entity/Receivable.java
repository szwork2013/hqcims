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
 * 应收Entity
 * @author wharlookingfor
 * @version 2013-05-12
 */
@Entity
@Table(name = "cms_receivable")
public class Receivable extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Long id; 		// 编号
	private Date create_date;	// 创建日期
	private String delFlag;	// 删除标记（0：正常；1：删除）
	private Order order;//订单
	private float amount;//应收金额
	private Consumer consumer;//客户
	private int status;
	

	public Receivable() {
		this.create_date = new Date();
		this.delFlag = DEL_FLAG_NORMAL;
		this.status=0;
	}

	public Receivable(Long id){
		this();
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	

	@NotNull
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@NotNull
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}




	@OneToOne(cascade = {CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="order_id")
	@NotNull
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}



	@NotNull
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@OneToOne(cascade = {CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="consumer_id")
	@NotNull
	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
	
}


