package com.freestyle.domain.view.echarts.pie;

import java.util.List;

public class Pie {

	private PieTitle pieTitle;
	
	private PieTooltip pieTooltip;
	
	private PieLegend pieLegend;
	
	private List<PieSeries> pieSeries;

	public Pie() {
		super();
	}
	public PieTitle getTitle() {
		return pieTitle;
	}

	public void setTitle(PieTitle pieTitle) {
		this.pieTitle = pieTitle;
	}

	public PieTooltip getTooltip() {
		return pieTooltip;
	}

	public void setTooltip(PieTooltip pieTooltip) {
		this.pieTooltip = pieTooltip;
	}

	public PieLegend getLegend() {
		return pieLegend;
	}

	public void setLegend(PieLegend pieLegend) {
		this.pieLegend = pieLegend;
	}

	public List<PieSeries> getSeries() {
		return pieSeries;
	}

	public void setSeries(List<PieSeries> pieSeries) {
		this.pieSeries = pieSeries;
	}
	
}
