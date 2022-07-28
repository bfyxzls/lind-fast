package com.lind.fast.demo.aop;

import com.lind.common.core.captcha.ArithmeticCaptcha;
import com.lind.fast.demo.anno.ResponseExcel;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lind
 * @date 2022/7/27 9:19
 * @since 1.0.0
 */
public class ResponseExcelReturnValueHandler implements HandlerMethodReturnValueHandler {

	@Override
	public boolean supportsReturnType(MethodParameter parameter) {
		return parameter.getMethodAnnotation(ResponseExcel.class) != null;
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 40);
		String result = captcha.text();
		System.out.printf("验证码计划结果=%s\n", result);
		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();
		captcha.out(sos);
		sos.close();
	}

}
