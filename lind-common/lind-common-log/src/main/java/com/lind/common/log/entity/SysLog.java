package com.lind.common.log.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SysLog {

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 日志类型
	 */
	@NotBlank(message = "日志类型不能为空")
	private String type;

	/**
	 * 日志标题
	 */
	@NotBlank(message = "日志标题不能为空")
	private String title;

	/**
	 * 操作IP地址
	 */
	private String remoteAddr;

	/**
	 * 用户浏览器
	 */
	private String userAgent;

	/**
	 * 请求URI
	 */
	private String requestUri;

	/**
	 * 操作方式
	 */
	private String method;

	/**
	 * 操作提交的数据
	 */
	private String params;

	/**
	 * 执行时间
	 */
	private Long time;

	/**
	 * 异常信息
	 */
	private String exception;

	/**
	 * 服务ID
	 */
	private String serviceId;

}
