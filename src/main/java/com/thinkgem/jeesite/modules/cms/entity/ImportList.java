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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 进货管理Entity
 * @author wharlookingfor
 * @version 2013-06-01
 */
@Entity
@Table(name = "cms_import_detail")
public class ImportList extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Long id; 		// 编号
	private Date create_date;	// 创建日期
	private String delFlag;	// 删除标记（0：正常；1：删除）
	

	private Float sale;//销售价格

	private Float rate;//进货价格

	private Float purchase;//进货价格

	private Integer num;//数量
	
	private Goods goods;//产品
	
	private ImportCart importCart;


	public ImportList() {
		this.create_date = new Date();
		this.delFlag = DEL_FLAG_NORMAL;
	}

	public ImportList(Long id){
		this();
		this.id = id;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cart_id")
	@NotNull
	public ImportCart getImportCart() {
		return importCart;
	}

	public void setImportCart(ImportCart importCart) {
		this.importCart = importCart;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cms_import")
	//@SequenceGenerator(name = "seq_cms_import", sequenceName = "seq_cms_import")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}



	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@NotNull
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@NotNull
	public Float getSale() {
		return sale;
	}

	public void setSale(Float sale) {
		this.sale = sale;
	}
	@NotNull
	public Float getPurchase() {
		return purchase;
	}

	public void setPurchase(Float purchase) {
		this.purchase = purchase;
	}
	@NotNull
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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

	@Override
	public String toString() {
		return "Imports [sale=" + sale + ", rate=" + rate + ", purchase="
				+ purchase + ", num=" + num +  "]";
	}
	
}


