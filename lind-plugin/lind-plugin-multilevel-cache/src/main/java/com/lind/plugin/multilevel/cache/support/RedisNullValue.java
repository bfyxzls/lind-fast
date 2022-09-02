package com.lind.plugin.multilevel.cache.support;

import lombok.Data;

import java.io.Serializable;

@Data
class RedisNullValue implements Serializable {

	private static final long serialVersionUID = 1L;

	public static RedisNullValue REDISNULLVALUE = new RedisNullValue();

}
