package com.freestyle.domain.view.echarts.graphs;

import java.util.List;

public class SeriesData {

	//设备上线
	private List<Long> online;
	
	//设备离线
	private List<Long> off_line; 
	
	//市电断电
	private List<Long> outage;
	
	//市电恢复
	private List<Long> recover;
	
	//电池低压
	private List<Long> battery_Low;
	
	//电池回复
	private List<Long> battery_recovery;
	
	//防拆报警
	private List<Long> tamper_alarm;

	public SeriesData() {
		super();
	}

	public List<Long> getOff_line() {
		return off_line;
	}

	public void setOff_line(List<Long> off_line) {
		this.off_line = off_line;
	}

	public List<Long> getBattery_Low() {
		return battery_Low;
	}

	public void setBattery_Low(List<Long> battery_Low) {
		this.battery_Low = battery_Low;
	}

	public List<Long> getOnline() {
		return online;
	}

	public void setOnline(List<Long> online) {
		this.online = online;
	}

	public List<Long> getOutage() {
		return outage;
	}

	public void setOutage(List<Long> outage) {
		this.outage = outage;
	}

	public List<Long> getRecover() {
		return recover;
	}

	public void setRecover(List<Long> recover) {
		this.recover = recover;
	}

	public List<Long> getBattery_recovery() {
		return battery_recovery;
	}

	public void setBattery_recovery(List<Long> battery_recovery) {
		this.battery_recovery = battery_recovery;
	}

	public List<Long> getTamper_alarm() {
		return tamper_alarm;
	}

	public void setTamper_alarm(List<Long> tamper_alarm) {
		this.tamper_alarm = tamper_alarm;
	}

}
