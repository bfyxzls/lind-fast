package com.lind.fast.demo.handler;

import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Date;

/**
 * @author lind
 * @date 2022/7/27 15:43
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class ImageCodeHandler implements HandlerFunction<ServerResponse> {

	@Override
	public ServerResponse handle(ServerRequest request) throws Exception {

		return ServerResponse.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body(DateUtil.now());
	}

}
