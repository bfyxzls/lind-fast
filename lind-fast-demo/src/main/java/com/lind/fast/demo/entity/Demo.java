package com.lind.fast.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author lind
 * @date 2022/8/31 11:28
 * @since 1.0.0
 */
@Data
public class Demo {
    @ExcelProperty(index = 0)
    private String username;

    @ExcelProperty(index = 1)
    private String password;
}
