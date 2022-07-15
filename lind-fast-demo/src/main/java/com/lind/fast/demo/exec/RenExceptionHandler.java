package com.lind.fast.demo.exec;

import cn.hutool.core.exceptions.ValidateException;
import com.lind.common.core.exception.ValidateCodeException;
import com.lind.common.core.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lind
 * @date 2022/7/15 11:06
 * @since 1.0.0
 */
@RestControllerAdvice
public class RenExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ValidateCodeException.class)
    public R handleRenException(ValidateCodeException ex) {

        return R.restResult(null, 400, ex.getMessage());
    }
}
