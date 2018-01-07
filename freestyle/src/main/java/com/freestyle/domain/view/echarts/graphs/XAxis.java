package com.freestyle.domain.view.echarts.graphs;

import java.util.List;

/**
 * 网格X轴
 * @author Xyf
 *
 */
public class XAxis {
	
	//X轴的类型
	private String type;
	
	//X轴的边界间隙
	private boolean boundaryGap;

	//X轴的数据
	private List<String> data;

	public XAxis() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBoundaryGap() {
		return boundaryGap;
	}

	public void setBoundaryGap(boolean boundaryGap) {
		this.boundaryGap = boundaryGap;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

}
