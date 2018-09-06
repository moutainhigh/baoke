package com.baoke.admin.util;

import java.util.List;

public class Filter {
	private String groupOp;
	List<Rules> rules;

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public List<Rules> getRules() {
		return rules;
	}

	public void setRules(List<Rules> rules) {
		this.rules = rules;
	}
}
