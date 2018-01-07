package com.freestyle.domain.view.echarts.graphs;

import java.util.List;

public class Echarts {

	private Title title;
	
	private Tooltip tooltip;
	
	private Legend legend;
	
	private Grid grid;
	
	private Toolbox toolbox;
	
	private XAxis xAxis;
	
	private YAxis yAxis;
	
	private List<Series> series;

	public Echarts() {
		super();
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public Legend getLegend() {
		return legend;
	}

	public void setLegend(Legend legend) {
		this.legend = legend;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Toolbox getToolbox() {
		return toolbox;
	}

	public void setToolbox(Toolbox toolbox) {
		this.toolbox = toolbox;
	}

	public XAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(XAxis xAxis) {
		this.xAxis = xAxis;
	}

	public YAxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(YAxis yAxis) {
		this.yAxis = yAxis;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

}
