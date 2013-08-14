/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 商品Entity
 * @author wharlookingfor
 * @version 2013-05-01
 */
public class GoodsCount extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Long id; 		// 编号
	@ExcelField(title="名称", align=2, sort=10)
	private String name; 	// 名称
	
	@ExcelField(title="备注", align=2, sort=90)
	private String remarks; // 备注

	@ExcelField(title="助记码", align=2, sort=20)
	private String code;//助记码
	
	@ExcelField(title="进价", align=2, sort=70)
	private String purchase;//进货价格
	
	@ExcelField(title="总金额", align=2, sort=80)
	private String total;//进货价格
	
	
	@ExcelField(title="规格", align=2, sort=30)
	private String brand;//规格
	

	@ExcelField(title="数量", align=2, sort=60)
	private Integer num;//数量


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getPurchase() {
		return purchase;
	}


	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}
	




	
	
}


