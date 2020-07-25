package com.example.springbootcommon.ldap;

import org.springframework.ldap.AuthenticationException;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

public class LdapTest {

    public static void main(String[] args) {
        try {
            authenticate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void authenticate() throws AuthenticationException {
        //1、获取用户名、密码
        String username = "tmailsl";
        String password = "sl-1234";

        String ldapUserName = username + "@dangdang.com";
        LdapTemplate ldapTemplate = getLdapTemplate(ldapUserName, password);
        boolean result = ldapAuthenticate(ldapTemplate, ldapUserName, password);
        if (!result) {
            throw new AuthenticationException();
        }
        System.out.println(result);
    }

    private static LdapTemplate getLdapTemplate(String username, String password) {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://10.64.4.240:389");
        contextSource.setUserDn(username);
        contextSource.setPassword(password);
        contextSource.setBase("OU=test,DC=dangdang,DC=com");
        contextSource.setPooled(false); // 不使用连接池 每次操作完成关闭连接
        contextSource.afterPropertiesSet(); // important
        return new LdapTemplate(contextSource);
    }

    private static boolean ldapAuthenticate(LdapTemplate template, String username, String password) {
        AndFilter andFilter = new AndFilter();
        String filter = "objectCategory=Person";
        String[] kv = filter.split("=");
        andFilter.and(new EqualsFilter(kv[0], kv[1]));
        andFilter.and(new EqualsFilter("userPrincipalName", username));
        return template.authenticate(DistinguishedName.EMPTY_PATH, andFilter.toString(), password);
    }
}
