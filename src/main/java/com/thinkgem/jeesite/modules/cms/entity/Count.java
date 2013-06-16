/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;

/**
 * 统计Entity
 * @author wharlookingfor
 * @version 2013-06-16
 */
public class Count extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String queryDate;	// 创建日期

	public Count() {
		this.queryDate = DateUtils.getDate();
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}


	
}


