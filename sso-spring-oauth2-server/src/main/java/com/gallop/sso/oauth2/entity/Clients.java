package com.gallop.sso.oauth2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author gallop
 * date 2021-06-25 18:01
 * Description:
 * Modified By:
 */
@Data
@Entity
@Table(schema = "ssoAuth", name = "oauth_client_details")
public class Clients implements Serializable {
    private static final long serialVersionUID = 1933343167622740615L;
    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "resource_ids")
    private String resourceIds;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "scope")
    private String scope;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name = "redirect_uri")
    private String redirectUri;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private int accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private int refreshTokenValidity;

    @Column(name = "additional_information")
    private String additionalInformation;

    @Column(name = "autoapprove")
    private String autoapprove;
}
