package com.mayday.common.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PagingVO implements Serializable {
	private int curPage = 1; 			 /* 현재 페이지 */
	private int rowSizePerPage = 10;	 /* 한 페이지당 입력 데이터 수 */
	private int pageSize = 10; 			 /* 페이지 리스트에서 보여줄 페이지 갯수*/
	private int totalRowCount; 			 /* 총 데이터 갯수 */
	
	private int firstRow;				/* 페이지에서 보여줄 첫번째 데이터*/
	private int lastRow;					/* 페이지에서 보여줄 마지막 데이터*/
	private int totalPageCount;			/* 페이지 총 개수 */
	private int firstPage;				/* 페이지 리스트에서 첫번째 페이지 */
	private int lastPage;				/* 페이지 리스트에서 마지막 페이지 */
	
	public void pageSetting() {
		totalPageCount = (int)Math.ceil((double)totalRowCount/rowSizePerPage);
		firstRow = (curPage -1)*rowSizePerPage +1;
		lastRow = (curPage==totalPageCount)?totalRowCount : curPage*rowSizePerPage;
		
		firstPage = (curPage -1)/ pageSize*pageSize+1;
		lastPage = firstPage+pageSize-1;
		if(lastPage>totalPageCount) lastPage = totalPageCount;
	}
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getRowSizePerPage() {
		return rowSizePerPage;
	}
	public void setRowSizePerPage(int rowSizePerPage) {
		this.rowSizePerPage = rowSizePerPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	
	
}
