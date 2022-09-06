package com.lind.common.mybatis.audit;

import java.util.List;

/**
 * @author lind
 * @date 2022/7/15 16:14
 * @since 1.0.0
 */
public interface CurrentAuditor {
    /**
     * 部门数据权限
     */
      List<Long> getDeptIdList();

    /**
     * 是否为管理员
     */
      Boolean getSuperAdmin();

    /**
     * 当前用户id
     * @return
     */
    Long getUserId();

	/**
	 * 当前用户名
	 * @return
	 */
	String getUserName();
}
