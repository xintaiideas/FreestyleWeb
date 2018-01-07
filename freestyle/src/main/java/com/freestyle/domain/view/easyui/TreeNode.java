package com.freestyle.domain.view.easyui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {
    
    public static final String STATE_OPEN = "open";
    public static final String STATE_CLOSED = "closed";

	private Long id;
	
	private String text;
	
	private String state;
	
	private List<TreeNode> children;
	
	private String iconCls;
	
	private Boolean checked;
	
	private Map<String,Object> attributes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    public void putAttribute(String key, Object value) {
        if(this.attributes == null) {
            this.attributes = new HashMap<String,Object>();
        } 
        this.attributes.put(key, value);
    }
    
    public void removeAttribute(String key) {
        if(this.attributes != null) {
            this.attributes.remove(key);
        }
    }

    public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
}
