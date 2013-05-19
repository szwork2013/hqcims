/** 
 * @Title: Balance1.java 
 * @Package com.thinkgem.jeesite.modules.cms.entity 
 * @Description: (用一句话描述该文件做什么) 
 * @author lookingfor
 * @date 2013-5-18 下午6:25:17 
 * @version V1.0  
 */ 
package com.thinkgem.jeesite.modules.cms.entity;

import java.io.Serializable;

/** 
 * @ClassName: Balance1 
 * @Description: (这里用一句话描述这个类的作用) 
 * @author lookingfor
 * @date 2013-5-18 下午6:25:17 
 *  
 */
public class Balance1 implements Serializable{
	/**
	 * @Fields serialVersionUID : (用一句话描述这个变量表示什么) 
	 */
	private static final long serialVersionUID = 7723734619291297636L;
	
	private String name;
	private String code;
	private int id;
	private float bamount;
	private float camount;
	private float ramount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getBamount() {
		return bamount;
	}
	public void setBamount(float bamount) {
		this.bamount = bamount;
	}
	public float getCamount() {
		return camount;
	}
	public void setCamount(float camount) {
		this.camount = camount;
	}
	public float getRamount() {
		return ramount;
	}
	public void setRamount(float ramount) {
		this.ramount = ramount;
	}
	
}
