package com.freestyle.service.admin.impl;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.freestyle.domain.entity.admin.SystemProperty;
import com.freestyle.service.admin.ISystemPropertyService;
import com.google.common.base.Strings;

@Service
public class SystemPropertyServiceImpl implements ISystemPropertyService {
    
//    @Autowired
//    private ISystemPropertyDao systemPropertyDao;
    
    @Override
    public void setProperty(String name, String value) {
        this.setProperty(name, null, value);
    }

    @Override
    public void setProperty(String oldName, String newName, Object value) {
        if(Strings.isNullOrEmpty(oldName)) throw new IllegalArgumentException("系统配置属性名称不能为空"); 
        String text = null;
        if(value != null) {
            Class<?> clazz = value.getClass();
            PropertyEditor editor = PropertyEditorManager.findEditor(clazz);
            if(editor == null) throw new IllegalArgumentException("属性编辑器" + clazz.toString() + "不支持");
            editor.setValue(value);
            text = editor.getAsText();
        }
        /*if(systemPropertyDao.existProperty(oldName)) {
            systemPropertyDao.updateProperty(oldName, newName, text);
        }else {
            systemPropertyDao.addProperty(oldName, text);
        }*/
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getProperty(String name, Class<T> clazz, T defaultValue) {
        if(Strings.isNullOrEmpty(name) || clazz == null) return defaultValue;
        PropertyEditor editor = PropertyEditorManager.findEditor(clazz);
        if(editor == null) return defaultValue;
        SystemProperty systemProperty = null;//systemPropertyDao.getProperty(name);
        if(systemProperty == null ||   Strings.isNullOrEmpty(systemProperty.getValue())) {
            return defaultValue;
        }
        editor.setAsText(systemProperty.getValue());
        Object obj = editor.getValue();
        if(obj == null || !clazz.equals(obj.getClass())) return defaultValue;
        return (T) obj;
    }

    @Override
    public String getAppName() {
        String appName = this.getProperty(ISystemPropertyService.APP_NAME, String.class, APP_NAME_DEFAULT);
        return appName;
    }

    @Override
    public String getAppVersion() {
        String appVersion = this.getProperty(ISystemPropertyService.APP_VERSION, String.class, APP_VERSION_DEFAULT);
        return appVersion;
    }

    @Override
    public String getCopyRight() {
        String copyRight = this.getProperty(ISystemPropertyService.COPY_RIGHT, String.class, COPY_RIGHT_DEFAULT);
        return copyRight;
    }

    @Override
    public String getCompanyName() {
        String companyName = this.getProperty(ISystemPropertyService.COMPANY_NAME, String.class, COMPANY_NAME_DEFAULT);
        return companyName;
    }

    @Override
    public void setAppName(String appName) {
        this.setProperty(ISystemPropertyService.APP_NAME, appName);
    }

    @Override
    public void setAppVersion(String appVersion) {
        this.setProperty(ISystemPropertyService.APP_VERSION, appVersion);
    }

    @Override
    public void setCopyRight(String copyRight) {
        this.setProperty(ISystemPropertyService.COPY_RIGHT, copyRight);
    }

    @Override
    public void setCompanyName(String companyName) {
        this.setProperty(ISystemPropertyService.COMPANY_NAME, companyName);
    }

    @Override
    public void deleteProperty(String name) {
//        this.systemPropertyDao.deleteProperty(name);
    }

    @Override
    public Map<String, String> getProperties() {
//        return this.systemPropertyDao.getProperties();
        return null;
    }

    @Override
    public boolean existProperty(String name) {
//        return this.systemPropertyDao.existProperty(name);
        return true;
    }

}
