package com.lind.common.mybatis.util;

import java.util.Optional;

public class DPHelper {

	protected static final ThreadLocal<Boolean> DATA_PERMISSION = new ThreadLocal<>();

	public static Boolean getLocalDataPermissions() {
		return Optional.ofNullable(DATA_PERMISSION.get()).orElse(false);
	}

	public static void setLocalDataPermissions(Boolean dataPermissions) {
		DATA_PERMISSION.set(dataPermissions);
	}

}
