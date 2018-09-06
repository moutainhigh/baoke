package com.baoke.admin.sys.domain;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private String id;
	private String text;
	private boolean leaf;
	private Integer sort;
	private List<Menu> children = null;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public void addChildren(Menu child) {
		if(children == null){
			children = new ArrayList<Menu>();
		}
		children.add(child);
	}
}
