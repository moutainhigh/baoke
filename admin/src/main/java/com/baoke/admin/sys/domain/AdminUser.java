package com.baoke.admin.sys.domain;
import java.io.Serializable;

import java.util.Date;
public class AdminUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6357979824345291980L;
	private Long id;
    private String userName;
    private String password;
    private String createdBy;
    private String updatedBy;
    private Long roleId;
    private String lastLoginIp;
    private Integer status;
    private Date lastLoginTime;
    private Date gmtCreate;
    private Date gmtModified;
    private String roleName;
    

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName=userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
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
   
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp=lastLoginIp;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status=status;
    }
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime=lastLoginTime;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}