package com.freestyle.domain.view.echarts.graphs;

/**
 * 网格
 * @author Xyf
 *
 */
public class Grid {

	//网格的距离左边的距离
	private String left;
	
	//网格距离右边的距离
	private String right;
	
	//网格距离顶部的长度
	private String bottom;
	
	//网格控制标签是否完全显示
	private boolean containLabel;

	public Grid() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

	public boolean isContainLabel() {
		return containLabel;
	}

	public void setContainLabel(boolean containLabel) {
		this.containLabel = containLabel;
	}
	
}
