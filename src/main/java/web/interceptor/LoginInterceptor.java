package web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import web.entity.TUser;
import web.holder.UserHolder;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    public UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handle) throws Exception{
        //OPTIONS放行
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            return true;
        }
        //登陆拦截
        TUser user = userService.loginValid(request.getHeader("token"),String.valueOf(System.currentTimeMillis()));
        if(user != null){
            request.setAttribute("user",user);
            UserHolder.setUser(user);
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) throws Exception{

    }
}
