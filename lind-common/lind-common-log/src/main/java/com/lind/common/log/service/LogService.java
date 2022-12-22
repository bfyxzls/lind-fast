package com.lind.common.log.service;

import com.lind.common.core.constant.SecurityConstants;
import com.lind.common.log.entity.SysLog;

/**
 * @author lind
 * @date 2022/12/22 16:01
 * @since 1.0.0
 */
public interface LogService {
   void saveLog(SysLog sysLog, String from);
}
