package com.restrant.entity;

public class Pager {
	private int curPage;//待显示页
	private int perPageRows ;//一页显示的记录数  
	private int rowCount; //记录总数  
	private int pageCount; //总页数 	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int currentPage) {
		this.curPage = currentPage;
	}
	public int getPerPageRows() {
		return perPageRows;
	}
	public void setPerPageRows(int perPageRows) {
		this.perPageRows = perPageRows;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		return (rowCount+perPageRows-1)/perPageRows;
	}
}
