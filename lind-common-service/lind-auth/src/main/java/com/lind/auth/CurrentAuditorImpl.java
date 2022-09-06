package com.lind.auth;

import com.lind.common.mybatis.audit.CurrentAuditor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lind
 * @date 2022/8/23 19:23
 * @since 1.0.0
 */
@Component
public class CurrentAuditorImpl implements CurrentAuditor {
    @Override
    public List<Long> getDeptIdList() {
        return null;
    }

    @Override
    public Boolean getSuperAdmin() {
        return null;
    }

    @Override
    public Long getUserId() {
        return null;
    }

	@Override
	public String getUserName() {
		return null;
	}
}
