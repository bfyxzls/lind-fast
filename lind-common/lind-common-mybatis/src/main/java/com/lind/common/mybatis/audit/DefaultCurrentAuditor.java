package com.lind.common.mybatis.audit;

import java.util.Collections;
import java.util.List;

/**
 * @author lind
 * @date 2022/10/10 17:08
 * @since 1.0.0
 */
public class DefaultCurrentAuditor implements CurrentAuditor {

	@Override
	public List<Long> getDeptIdList() {
		return Collections.emptyList();
	}

	@Override
	public Boolean getSuperAdmin() {
		return false;
	}

	@Override
	public Long getUserId() {
		return 0L;
	}

	@Override
	public String getUserName() {
		return "none";
	}

}
