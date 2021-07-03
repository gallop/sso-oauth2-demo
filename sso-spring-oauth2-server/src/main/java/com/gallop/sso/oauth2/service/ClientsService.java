package com.gallop.sso.oauth2.service;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * author gallop
 * date 2021-06-28 10:52
 * Description:
 * Modified By:
 */
public interface ClientsService {
    BaseClientDetails getByClientId(String clientId);
}
