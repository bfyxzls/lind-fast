package com.lind.fast.demo;

import com.lind.common.mybatis.base.CurrentAuditor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author lind
 * @date 2022/8/3 15:55
 * @since 1.0.0
 */
@Component
public class CurrentAuditorImpl implements CurrentAuditor {
    @Override
    public List<Long> getDeptIdList() {
        return Arrays.asList(1L,2L,3L);
    }

    @Override
    public Boolean getSuperAdmin() {
        return false;
    }

    @Override
    public Long getUserId() {
        return 1L;
    }
}
