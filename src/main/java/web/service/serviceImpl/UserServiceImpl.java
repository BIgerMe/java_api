package web.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import web.entity.TUser;
import web.repository.UserRepository;
import web.service.UserService;
import org.springframework.stereotype.Service;
import web.utils.SaltMD5Util;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public TUser loginService(String username, String password) throws Exception {
        TUser user = userRepository.findFirstByUsername(username);
        if(user != null){
            boolean passwordFlat = SaltMD5Util.verifySaltPassword(password,user.getPassword());
            if(passwordFlat){
                long timestamp = System.currentTimeMillis();
                if(user.getTokenExpire() == null || Long.parseLong(user.getTokenExpire()) < timestamp){
                    String token = UUID.randomUUID().toString();
                    user.setToken(token);
                }
                user.setTokenExpire(String.valueOf(timestamp+86400*3000));//默认3天
                userRepository.saveAndFlush(user);
                user.setPassword("");
                return user;
            }else{
                throw new Exception("用户名密码不正确！");
            }
        }
        throw new Exception("用户不存在！");
    }

    @Override
    public TUser registerService(TUser user) {
        if(userRepository.findFirstByUsername(user.getUsername()) != null){
            return null;
        }else {
            user.setPassword(SaltMD5Util.generateSaltPassword(user.getPassword()));
            TUser newUser = userRepository.save(user);
            if (newUser != null) {
                newUser.setPassword("");
            }
            return newUser;
        }
    }

    @Override
    public TUser loginValid(String token,String tokenExpire){
        return userRepository.findFirstByTokenAndTokenExpireAfter(token,tokenExpire);
    }
}
