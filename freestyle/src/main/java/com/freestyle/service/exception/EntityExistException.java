package com.freestyle.service.exception;

/**
 * 实体已存在异常
 * @author 大爱阳哥
 *
 */
public class EntityExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Class<?> entityClazz;

	public EntityExistException(String message, Class<?> entityClazz) {
		super(message);
		this.entityClazz = entityClazz;
	}

	public Class<?> getEntityClazz() {
		return entityClazz;
	}
}
