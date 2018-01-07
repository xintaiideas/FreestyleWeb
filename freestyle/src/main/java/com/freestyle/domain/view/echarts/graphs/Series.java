package com.freestyle.domain.view.echarts.graphs;

import java.util.List;
import java.util.Map;

/**
 * 网格中的数据
 * @author Xyf
 *
 */
public class Series {

	//线的数据
	private List<Long> data;
	
	//线的名称
	private String name; 
	
	//线的类型
	private String type;
	
	//线的系列
	private String stack;

	public Series() {
		super();
	}
	
	public String getType() {
		return type;
	}

	public List<Long> getData() {
		return data;
	}

	public void setData(List<Long> data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}
	
}
