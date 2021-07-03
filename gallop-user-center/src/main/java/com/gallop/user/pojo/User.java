package com.gallop.user.pojo;

import lombok.Data;

/**
 * author gallop
 * date 2021-06-23 9:13
 * Description:
 * Modified By:
 */
@Data
public class User {
    private String id;
    /**用户名*/
    private String loginName;

    /** 姓名 */
    private String fullName;
    /** 手机 */
    private String mobile;

    /** 性别 */
    private String sex;

    /** 邮箱*/
    private String email;

    /** 职务*/
    private String post;

    private String code;
}
