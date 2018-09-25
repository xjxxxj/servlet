package com.xjx.pojo;

public class Page {

	private Integer pageNum = 1 ;//当前页码，默认第一页
	private Integer itemCount = 5 ;//每页条数，默认5条
	private Integer allItemCount ;//总条数
	private Integer allPageCount ;//总页数
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	public Integer getAllItemCount() {
		return allItemCount;
	}
	public void setAllItemCount(Integer allItemCount) {
		this.allItemCount = allItemCount;
	}
	public Integer getAllPageCount() {
		return allPageCount;
	}
	public void setAllPageCount(Integer allPageCount) {
		this.allPageCount = allPageCount;
	}
	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", itemCount=" + itemCount + ", allItemCount=" + allItemCount
				+ ", allPageCount=" + allPageCount + "]";
	}
	
	
	
}
