package com.gallop.user.sso;

import com.gallop.common.base.BaseResult;
import com.gallop.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * author gallop
 * date 2021-06-17 17:17
 * Description:
 * Modified By:
 */
@Service
@Slf4j
public class SsoServiceOtherImpl extends SsoServiceBasic{
    //implements InitializingBean
    @Override
    public SsoTypeEnum ssoType() {
        return SsoTypeEnum.OTHER;
    }

    @Override
    public BaseResult login(User user, HttpServletRequest request, HttpServletResponse response) {

        BaseResult baseResult= returnspot(user,request,  response);
        return baseResult;
    }

   /* @Override
    public void afterPropertiesSet() throws Exception {

    }*/
}
