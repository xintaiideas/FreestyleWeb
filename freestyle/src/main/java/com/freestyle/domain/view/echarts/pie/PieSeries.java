package com.freestyle.domain.view.echarts.pie;

import java.util.List;

public class PieSeries {

	private String name;
	
	private String type;
	
	private String radius;
	
	private List<String> center;
	
	private List<PieData> pieData;
	
	private PieItemStyle pieItemStyle;

	public PieSeries() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public List<String> getCenter() {
		return center;
	}

	public void setCenter(List<String> center) {
		this.center = center;
	}


	public List<PieData> getData() {
		return pieData;
	}

	public void setData(List<PieData> pieData) {
		this.pieData = pieData;
	}

	public PieItemStyle getItemStyle() {
		return pieItemStyle;
	}

	public void setItemStyle(PieItemStyle pieItemStyle) {
		this.pieItemStyle = pieItemStyle;
	}
	
}
