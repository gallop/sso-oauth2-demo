package com.gallop.sso.oauth2.service.impl;

import com.gallop.sso.oauth2.entity.Clients;
import com.gallop.sso.oauth2.repository.ClientsRepository;
import com.gallop.sso.oauth2.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * author gallop
 * date 2021-06-28 10:53
 * Description:
 * Modified By:
 */
@Service
public class ClientsServiceImpl implements ClientsService {
    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    public BaseClientDetails getByClientId(String clientId) {
        Clients clients = clientsRepository.findById(clientId);
        if(clients==null){
            return null;
        }
        BaseClientDetails clientDetails = new BaseClientDetails(clients.getClientId(),clients.getResourceIds(),clients.getScope(),clients.getAuthorizedGrantTypes(),clients.getAuthorities(),clients.getRedirectUri());
        clientDetails.setClientSecret(clients.getClientSecret());
        clientDetails.setAccessTokenValiditySeconds(clients.getAccessTokenValidity());
        clientDetails.setRefreshTokenValiditySeconds(clients.getRefreshTokenValidity());
        Set scopeList;
        scopeList = StringUtils.commaDelimitedListToSet(clients.getAutoapprove());
        if (!scopeList.isEmpty()) {
            clientDetails.setAutoApproveScopes(scopeList);
        }

        return clientDetails;
    }
}
