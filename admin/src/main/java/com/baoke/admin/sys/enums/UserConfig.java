package com.baoke.admin.sys.enums;

public enum UserConfig {

	IS_LOGIN("IS_LOGIN","是否登录的KEY"),
	USER_ID("USER_ID","用户ID的KEY"),
	USER_NAME("USER_NAME","用户NAME的KEY"),
	;
    
	UserConfig(String value,String name){
        this.value = value;
        this.name = name;
    }
    	
    private String value;
    private String name;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
