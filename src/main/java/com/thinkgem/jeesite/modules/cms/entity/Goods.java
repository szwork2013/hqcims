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

/**
 * 商品Entity
 * @author wharlookingfor
 * @version 2013-05-01
 */
@Entity
@Table(name = "cms_goods")
public class Goods extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Long id; 		// 编号
	private String name; 	// 名称
	private String remarks; // 备注
	private Date create_date;	// 创建日期
	private String delFlag;	// 删除标记（0：正常；1：删除）
	private String code;//助记码
	private float purchase;//进货价格
	private float sale;//销售价格
	private float rate;//销售点数
	private String origin;//产地
	private String brand;//规格
	private Date update_date;//最近更新时间
	private String unit;
	private int num;//数量
	public Goods() {
		this.create_date = new Date();
		this.update_date=new Date();
		this.delFlag = DEL_FLAG_NORMAL;
	}

	public Goods(Long id){
		this();
		this.id = id;
		this.update_date=new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cms_goods")
	//@SequenceGenerator(name = "seq_cms_goods", sequenceName = "seq_cms_goods")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@NotNull
	@Length(min=1, max=50)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@NotNull
	@Length(min=1, max=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@NotNull
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@NotNull
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NotNull
	public float getPurchase() {
		return purchase;
	}

	public void setPurchase(float purchase) {
		this.purchase = purchase;
	}

	@NotNull
	public float getSale() {
		return sale;
	}

	public void setSale(float sale) {
		this.sale = sale;
		
	}

	@NotNull
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Length(min=0, max=200)
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Length(min=0, max=200)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@NotNull
	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	
}


