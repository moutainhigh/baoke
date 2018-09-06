package com.baoke.admin.sys.domain;
import java.io.Serializable;

import java.util.Date;
public class AdminOperatorLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7435077573352565734L;
	private Long id;
    private Long userId;
    private Long roleId;
    private String url;
    private String description;
    private Date gmtCreate;
    private Date gmtModified;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId=userId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}