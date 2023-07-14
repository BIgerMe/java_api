package web.service;

import web.entity.TUser;

public interface UserService {
    TUser loginService(String username, String password) throws Exception;
    TUser registerService(TUser user);
    TUser loginValid(String token,String tokenExpire);
}
