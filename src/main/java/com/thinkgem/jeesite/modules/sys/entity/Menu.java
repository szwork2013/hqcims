/** 
 * @Title: Menu.java 
 * @Package com.hqc.ims.entity 
 * @Description: (用一句话描述该文件做什么) 
 * @author lookingfor
 * @date 2013-4-29 下午6:09:43 
 * @version V1.0  
 */ 
package com.thinkgem.jeesite.modules.sys.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.IdEntity;


/** 
 * @ClassName: Menu 
 * @Description: (这里用一句话描述这个类的作用) 
 * @author lookingfor
 * @date 2013-4-29 下午6:09:43 
 *  
 */
@Entity
@Table(name="tb_menu")
public class Menu extends IdEntity{
	private String name;//菜单名称
	private String href;//菜单连接
	private String target;//连接的目标  mainFrame main等
	private String icon;//图标
	private Menu parent;

	private List<Menu> childList = Lists.newArrayList();// 拥有子菜单列表
	

	
	@ManyToOne
	@JoinColumn(name="parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
    public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	

	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY,mappedBy="parent")
	@NotFound(action = NotFoundAction.IGNORE)
	public List<Menu> getChildList() {
		return childList;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", href=" + href + ", target=" + target
				+ ", icon=" + icon + ", sss="+id+"]";
	}
	


}
