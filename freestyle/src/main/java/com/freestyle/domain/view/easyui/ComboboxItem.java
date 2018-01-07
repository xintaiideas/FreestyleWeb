package com.freestyle.domain.view.easyui;

import java.io.Serializable;

/**
 * easyui Combobox Item对象
 * @author right
 *
 */
public class ComboboxItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String value;	//	值
	
	private String text;		//	显示文本
	
	private String group;	//	分组名称
	
	private Boolean selected;	//	是否选中

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
