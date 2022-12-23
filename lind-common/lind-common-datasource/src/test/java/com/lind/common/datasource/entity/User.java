package com.lind.common.datasource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lind
 * @date 2022/12/23 9:54
 * @since 1.0.0
 */
@Data
@TableName("user")
public class User {

	Integer id;

	String name;

}
