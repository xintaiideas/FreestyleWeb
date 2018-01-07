package com.freestyle.service.exception;

/**
 * 实体不存在异常
 * @author Leo Lien
 * 2016年10月20日 下午5:52:48 创建
 */
public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private Class<?> entityClazz;

    public EntityNotFoundException(String message, Class<?> entityClazz) {
        super(message);
        this.entityClazz = entityClazz;
    }

	public Class<?> getEntityClazz() {
		return entityClazz;
	}

}
