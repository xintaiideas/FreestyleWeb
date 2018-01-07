package com.freestyle.domain.view.echarts.pie;

public class PieEmphasis {

	private int shadowBlur;
	
	private int shadowOffsetX;
	
	private String shadowColor;

	public PieEmphasis() {
		super();
	}

	public int getShadowBlur() {
		return shadowBlur;
	}

	public void setShadowBlur(int shadowBlur) {
		this.shadowBlur = shadowBlur;
	}

	public int getShadowOffsetX() {
		return shadowOffsetX;
	}

	public void setShadowOffsetX(int shadowOffsetX) {
		this.shadowOffsetX = shadowOffsetX;
	}

	public String getShadowColor() {
		return shadowColor;
	}

	public void setShadowColor(String shadowColor) {
		this.shadowColor = shadowColor;
	}
	
}
