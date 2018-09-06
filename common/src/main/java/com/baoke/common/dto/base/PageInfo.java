package com.baoke.common.dto.base;

import java.io.Serializable;

public class PageInfo implements Serializable {
	private static final long serialVersionUID = 7553973735794350843L;

	/**
	 * 总记录数 默认为-1
	 */
	private int total = 0;

	/**
	 * 总页数
	 */
	private int totalPages;

	/**
	 * 每页显示的记录数
	 */
	private int pageSize = 12;

	/**
	 * 当前所在页码（在第几页）
	 */
	private int current = 1;

	private int startPageNum;// 开始查询的条数(limit第一个参数)

	public PageInfo() {
	}

	public PageInfo(Integer current, Integer pageSize) {
		if (current != null) {
			this.current = current;
		}
		if (pageSize != null) {
			this.pageSize = pageSize;
		}
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setTotal(int total) {
		this.total = total;

		if (this.total == 0) {
			this.totalPages = this.total;
		} else if (this.total < this.pageSize) {
			this.totalPages = 1;
		} else if (this.total % this.pageSize == 0) {
			this.totalPages = this.total / this.pageSize;
		} else {
			this.totalPages = (this.total / this.pageSize) + 1;
		}
	}

	public int getStartPageNum() {
		return (current - 1) * pageSize;
	}

	public void setStartPageNum() {
		this.startPageNum = (current - 1) * pageSize;
	}
}
