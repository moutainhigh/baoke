package com.baoke.admin.sys.domain;
import java.io.Serializable;

import java.util.Date;
public class AdminMenu implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final long IS_SHOW_TRUE = 1;
	public static final long IS_SHOW_FALSE = 0;
	
	private Long id;
    /**
     * 角色tab
     */
    
    private String menuTab;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 菜单是否显示
     */
    private int isShow;
    /**
     * 菜单序号
     */
    private Integer sort;
    /**
     * 菜单链接
     */
    private String url;
    /**
     * 菜单描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 更新人
     */
    private String updatedBy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMenuTab() {
		return menuTab;
	}
	public void setMenuTab(String menuTab) {
		this.menuTab = menuTab;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
    
}