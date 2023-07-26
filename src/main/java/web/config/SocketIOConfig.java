package web.config;


import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import web.entity.TUser;
import web.service.UserService;

import java.util.List;
import java.util.Map;

@Configuration
public class SocketIOConfig {
    private UserService userService;
    public SocketIOConfig(UserService userService) {
        this.userService = userService;
    }
    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost");
        config.setPort(9090);

        final SocketIOServer server = new SocketIOServer(config);
        //添加认证监听器
        server.addConnectListener(new AuthorizationListener(userService));
        server.start();

        return server;
    }

    private static class AuthorizationListener implements ConnectListener{
        private final UserService userService;
        public AuthorizationListener(UserService userService) {
            this.userService = userService;
        }

        @Override
        public void onConnect(SocketIOClient client){
            // 获取客户端传递的认证信息，例如从 HTTP 请求中提取用户信息
            Object userInfo = extractUserInfoFromHttpRequest(client.getHandshakeData().getUrlParams());
            // 存储用户信息到会话中
            client.set("user", userInfo);
        }

        private Object extractUserInfoFromHttpRequest(Map<String, List<String>> urlParams){
            TUser user = userService.loginValid(urlParams.get("token").get(0),String.valueOf(System.currentTimeMillis()));
            return user;
        }
    }
}
