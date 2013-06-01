/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.entity;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 换货Entity
 * @author wharlookingfor
 * @version 2013-05-29
 */
@Entity
@Table(name = "cms_change")
public class Change extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
    private Long id; 		// 编号
	private User user; 		// 用户
	private String remarks; // 备注
    private Date createDate;	// 创建日期
    private String delFlag;	// 删除标记（0：正常；1：删除）
    private Consumer consumer;//客户
    private Goods goods;//产品
    private int num=1;


	public Change() {
		this.createDate = new Date();
		this.delFlag = DEL_FLAG_NORMAL;
	}

	public Change(Long id){
		this();
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cms_change")
	//@SequenceGenerator(name = "seq_cms_change", sequenceName = "seq_cms_change")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public int getNum() {
        return num;
    }
    @NotNull
    public void setNum(int num) {
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

    @OneToOne(cascade = {CascadeType.REFRESH},fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    @NotNull
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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


	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@NotNull
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}


