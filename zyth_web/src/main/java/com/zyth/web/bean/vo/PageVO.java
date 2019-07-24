package com.zyth.web.bean.vo;

public class PageVO extends Object {


    public boolean equals(Object obj) {
        return (this == obj);
    }

	private int start;
    private int length;

	private int currentPage;
    private int perPageNum;

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	public int getStartNum() {
		return (currentPage-1)*perPageNum;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

}
