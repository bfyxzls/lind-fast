package com.lind.common.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lind.common.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author lind
 * @date 2022/10/10 16:20
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_attribute")
public class UserAttribute extends BaseEntity {

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;

	@TableField("user_id")
	private String userId;

	@NotBlank(message = "名称不能为空")
	private String name;

	@NotBlank(message = "值不能为空")
	private String value;

}
