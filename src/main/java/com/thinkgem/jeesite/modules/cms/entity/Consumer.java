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
 * 客户维护Entity
 * @author wharlookingfor
 * @version 2013-04-30
 */
@Entity
@Table(name = "cms_consumer")
public class Consumer extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	@ExcelField(title="ID", type=1, align=2, sort=1)
	private Long id; 		// 编号
	
	@ExcelField(title="名称", align=2, sort=10)
	private String name; 	// 名称
	
	@ExcelField(title="助记码", align=2, sort=20)
	private String code;//记住码
	
	@ExcelField(title="地址", align=2, sort=30)
	private String address;//地址
	
	@ExcelField(title="店铺名称", align=2, sort=40)
	private String shop_name;//店铺名称
	
	@ExcelField(title="店铺电话", align=2, sort=50)
	private String shop_phone;//店铺联系电话
	
	@ExcelField(title="联系方式", align=2, sort=60)
	private String phone;//客户联系方式
	
	@ExcelField(title="是否代收客户", align=2, sort=70,dictType="yes_no")
	private Integer is_consumer;//是否代收客户  0是 1否
	
	@ExcelField(title="是否供应商客户", align=2, sort=80,dictType="yes_no")
	private Integer is_provider;//是否是供应商客户 0是1否
	
	@ExcelField(title="备注", align=2, sort=90)
	private String remarks; // 备注
	private Date create_date;	// 创建日期
	private String del_flag;	// 删除标记（0：正常；1：删除）
	private int is_special;//0正常客户 1散户

	public Consumer() {
		this.is_special=0;
		this.create_date = new Date();
		this.del_flag = DEL_FLAG_NORMAL;
	}

	public Consumer(Long id){
		this();
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cms_consumer")
	//@SequenceGenerator(name = "seq_cms_consumer", sequenceName = "seq_cms_consumer")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
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
	
	
//	@NotNull
//	public Date getCreateDate() {
//		return createDate;
//	}
//
//	public void setCreateDate(Date createDate) {
//		this.createDate = createDate;
//	}

	

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

	@Length(min=1, max=45)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_phone() {
		return shop_phone;
	}

	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIs_consumer() {
		return is_consumer;
	}

	public void setIs_consumer(Integer is_consumer) {
		this.is_consumer = is_consumer;
	}

	public Integer getIs_provider() {
		return is_provider;
	}

	public void setIs_provider(Integer is_provider) {
		this.is_provider = is_provider;
	}

	public int getIs_special() {
		return is_special;
	}

	public void setIs_special(int is_special) {
		this.is_special = is_special;
	}
	
	
}


