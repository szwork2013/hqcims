/*
 * Copyright (c) 2013.
 * whatlookingfor@gmail.com
 */

/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.entity;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 退物车Entity
 * @author wharlookingfor
 * @version 2013-05-03
 */
@Entity
@Table(name = "cms_import_cart")
public class ImportCart extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Long id; 		// 编号
	private Consumer consumer;//用户
	private Long user_id;//登陆ID
	private Date create_date;	// 创建日期
	private String del_flag;	// 删除标记（0：正常；1：删除）
	private List<ImportList> childList;
	public ImportCart() {
		this.create_date = new Date();
		this.del_flag = DEL_FLAG_NORMAL;
	}

	public ImportCart(Long id){
		this();
		this.id = id;
	}
	
	@OneToMany(cascade = {CascadeType.REFRESH},fetch=FetchType.EAGER,mappedBy="importCart")
	@OrderBy(value="id desc")
	public List<ImportList> getChildList() {
		return childList;
	}

	public void setChildList(List<ImportList> childList) {
		this.childList = childList;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Length(min=1, max=1)
	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}


	@NotNull
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	
	
	
	
}


