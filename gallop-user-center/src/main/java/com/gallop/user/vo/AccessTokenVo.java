package com.gallop.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * author gallop
 * date 2021-06-15 17:47
 * Description:
 * Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenVo implements Serializable {
    private static final long serialVersionUID = 6019335911289014107L;

    private String access_token;
    private String refresh_token;
    private int expires_in;
}
