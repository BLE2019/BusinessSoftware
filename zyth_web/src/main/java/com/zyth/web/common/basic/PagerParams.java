package com.zyth.web.common.basic;

import java.io.Serializable;
import java.util.List;

public class PagerParams<T> implements Serializable {

	/**
	 * 分页Bean
	 */
	private static final long serialVersionUID = 1L;

	private int queryTotal;

	private int start;

	private int end;

	private List<T> rows;

	private int page;

	private int rp;

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	private int pageCount;

	public int getPageCount() {
		pageCount = (page - 1) * rp;
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		start = (page - 1) * rp;
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		end = page * rp;
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(int queryTotal) {
		this.queryTotal = queryTotal;
	}

	private int total;

	public int getTotal() {
		int totalTemp = 0;
		if (rp != 0) {
			totalTemp = this.getQueryTotal();
		} else {
			totalTemp = 1;
		}
		total = totalTemp;
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
