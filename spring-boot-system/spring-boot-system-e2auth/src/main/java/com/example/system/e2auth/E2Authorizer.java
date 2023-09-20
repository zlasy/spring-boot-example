package com.example.system.e2auth;

import com.example.system.authorize.LoginAuthorizer;
import com.example.system.authorize.pojo.LoginAuthorization;
import com.example.system.user.User;

public class E2Authorizer implements LoginAuthorizer {

    @Override
    public LoginAuthorization authorize(User user) {
        return null;
    }
}
