package com.lind.fast.demo.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lind
 * @date 2022/7/26 14:44
 * @since 1.0.0
 */
@Component
public class SimpleJob {

	static Logger logger = LoggerFactory.getLogger(SimpleJob.class);

	@XxlJob("testDemoJobHandler")
	public ReturnT<String> testDemoJobHandler(String param) throws Exception {
		System.out.println(new Date() + " testJobHandler hello");
		Thread.sleep(10000);
		return ReturnT.SUCCESS;
	}

}
