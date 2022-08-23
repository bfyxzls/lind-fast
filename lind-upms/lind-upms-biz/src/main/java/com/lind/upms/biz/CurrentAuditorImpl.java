package com.lind.upms.biz;

import com.lind.common.mybatis.base.CurrentAuditor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
