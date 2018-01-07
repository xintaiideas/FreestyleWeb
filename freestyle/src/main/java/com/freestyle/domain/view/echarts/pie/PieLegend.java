package com.freestyle.domain.view.echarts.pie;

import java.util.List;

public class PieLegend {

	private String orient;
	
	private String left;
	
	private List<String> data;

	public PieLegend() {
		super();
	}

	public String getOrient() {
		return orient;
	}

	public void setOrient(String orient) {
		this.orient = orient;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
	
}
