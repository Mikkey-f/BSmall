package com.mikkeyf.bsmall.form;

import lombok.Data;


/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.form
 * @className: UserForm
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/4 22:25
 * @version: 1.0
 */
@Data
public class UserRegisterForm {

    private String username;

    private String password;

    private String email;
}
