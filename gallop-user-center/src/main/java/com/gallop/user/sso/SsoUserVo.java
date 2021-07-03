package com.gallop.user.sso;

import lombok.Data;

import java.io.Serializable;

/**
 * author gallop
 * date 2021-06-21 16:08
 * Description:
 * Modified By:
 */
@Data
public class SsoUserVo implements Serializable {
    private static final long serialVersionUID = 675787371439036143L;

    private String bimRequestId;
    private String bimRemoteUser;
    private String bimRemotePwd;
    private String bimUid;
    private String loginName;
    private String organizationId;
    private String organizationName;
    private String fullName;

    private String idCard;
    private String mobile;
    private String sex;
    private String email;
    private String post;
}
