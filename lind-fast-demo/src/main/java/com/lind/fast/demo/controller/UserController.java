package com.lind.fast.demo.controller;

import com.lind.common.core.util.R;
import com.lind.common.core.validator.ValidatorUtils;
import com.lind.common.core.validator.group.AddGroup;
import com.lind.common.core.validator.group.DefaultGroup;
import com.lind.fast.demo.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lind
 * @date 2022/7/15 10:58
 * @since 1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping
    public R ds(@RequestBody UserDTO userDTO) {
        //只验证声明了AddGroup组的验证注释，本例中是phone和email
        ValidatorUtils.validateEntity(userDTO, AddGroup.class);
        return R.ok();
    }
}
