package web.controller;

import web.entity.TUser;
import web.service.UserService;
import web.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "只是测试";
    }

    @PostMapping("/login")
    public Result<TUser> login(@RequestParam String username, @RequestParam String password){
        try{
            TUser user = userService.loginService(username,password);
            return Result.success(user,"登录成功！");
        }catch (Exception ex){
            return Result.error(-1,ex.getMessage());
        }
    }

    @PostMapping("/register")
    public Result<TUser> register(@RequestBody TUser newUser){
        TUser user = userService.registerService(newUser);
        if(user != null){
            return Result.success(user,"注册成功！");
        }else{
            return Result.error(-1,"用户名已存在!");
        }
    }
}
