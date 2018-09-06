package com.baoke.admin.sys.domain;
import java.io.Serializable;

import java.util.Date;
public class AdminRole implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6462269580359822678L;
	private Long id;
    private String roleName;
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

	public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName=roleName;
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