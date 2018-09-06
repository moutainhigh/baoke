package com.baoke.admin.sys.domain;
import java.io.Serializable;

import java.util.Date;
public class AdminRolePermission implements Serializable {
	private static final long serialVersionUID = 7124407769518658561L;
	private Long id;
    private Long roleId;
    private String url;
    private String description;
    private String createdBy;
    private String updatedBy;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModified;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId=roleId;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url=url;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy=createdBy;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy=updatedBy;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status=status;
    }
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate=gmtCreate;
    }
    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified=gmtModified;
    }
}