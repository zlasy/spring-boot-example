package com.example.system.authorize;

import com.example.system.authorize.pojo.LoginAuthorization;
import com.example.system.user.User;

public interface LoginAuthorizer {

    LoginAuthorization authorize(User user);
}
