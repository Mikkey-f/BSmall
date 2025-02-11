package com.mikkeyf.bsmall.form;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.form
 * @className: UserLoginForm
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/4 23:40
 * @version: 1.0
 */
@Data
public class UserLoginForm {

    private String username;

    private String password;

    private boolean rememberMe;
}
