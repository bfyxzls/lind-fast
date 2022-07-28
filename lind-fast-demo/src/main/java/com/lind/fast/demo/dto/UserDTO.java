package com.lind.fast.demo.dto;

import com.lind.common.core.validator.group.AddGroup;
import com.lind.common.core.validator.group.DefaultGroup;
import com.lind.common.core.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author lind
 * @date 2022/7/15 10:55
 * @since 1.0.0
 */
@Data
public class UserDTO {
    @Null(message = "{id.null}", groups = AddGroup.class)
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;

    @NotBlank(message = "{name.require}", groups = DefaultGroup.class)
    private String username;

    @NotBlank(message = "{phone.require}", groups = AddGroup.class)
    private String phone;

    @NotBlank(message = "{email.require}", groups = AddGroup.class)
    @Email(message = "{email.error}", groups = DefaultGroup.class)
    private String email;
}
