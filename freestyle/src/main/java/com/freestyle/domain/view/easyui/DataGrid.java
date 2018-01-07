package com.freestyle.domain.view.easyui;

import java.io.Serializable;
import java.util.List;

/**
 * easyUI datagrid数据表格
 * @author right
 *
 * @param <T>
 */
public class DataGrid<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer total;	//	总条数
	
	private List<T> rows;	//	数据集合
	
	private List<T> footer;	//	底部数据集合

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List<T> getFooter() {
		return footer;
	}

	public void setFooter(List<T> footer) {
		this.footer = footer;
	}

}
