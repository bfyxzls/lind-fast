package com.lind.fast.demo.config;

import com.lind.common.log.entity.SysLog;
import com.lind.common.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lind
 * @date 2022/12/22 16:06
 * @since 1.0.0
 */
@Component
@Slf4j
public class LogServiceImpl implements LogService {

	@Override
	public void saveLog(SysLog sysLog, String from) {
		log.info("sysLog={}", sysLog);
	}

}
