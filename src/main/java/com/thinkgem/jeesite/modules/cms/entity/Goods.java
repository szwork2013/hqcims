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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

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
	
	@ExcelField(title="名称", align=2, sort=10)
	private String name; 	// 名称
	
	@ExcelField(title="备注", align=2, sort=100)
	private String remarks; // 备注
	
	private Date create_date;	// 创建日期
	private String delFlag;	// 删除标记（0：正常；1：删除）
	
	@ExcelField(title="助记码", align=2, sort=20)
	private String code;//助记码
	
	@ExcelField(title="进货价格", align=2, sort=70)
	private Float purchase;//进货价格
	
	@ExcelField(title="销售价格", align=2, sort=80)
	private Float sale;//销售价格
	
	@ExcelField(title="销售点数", align=2, sort=90)
	private Float rate;//销售点数
	
	@ExcelField(title="生产厂家", align=2, sort=40)
	private String origin;//产地
	
	@ExcelField(title="规格", align=2, sort=30)
	private String brand;//规格
	
	private Date update_date;//最近更新时间
	
	@ExcelField(title="单位", align=2, sort=50)
	private String unit;
	
	@ExcelField(title="数量", align=2, sort=60)
	private Integer num;//数量
	

	
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
	@ExcelField(title="ID", type=1, align=2, sort=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
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
	public Float getPurchase() {
		return purchase;
	}

	public void setPurchase(Float purchase) {
		this.purchase = purchase;
	}

	@NotNull
	public Float getSale() {
		return sale;
	}

	public void setSale(Float sale) {
		this.sale = sale;
		
	}

	@NotNull
	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
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



	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", remarks=" + remarks
				+ ", create_date=" + create_date + ", delFlag=" + delFlag
				+ ", code=" + code + ", purchase=" + purchase + ", sale="
				+ sale + ", rate=" + rate + ", origin=" + origin + ", brand="
				+ brand + ", update_date=" + update_date + ", unit=" + unit
				+ ", num=" + num + "]";
	}
	
	
}


