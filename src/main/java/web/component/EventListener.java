package web.component;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EventListener {
    private final SocketIOServer socketIOServer;

    @Autowired
    public EventListener(SocketIOServer socketIOServer){
        this.socketIOServer = socketIOServer;
        registerEventHandlers();
    }

    public void registerEventHandlers(){
        socketIOServer.addEventListener("chat", Object.class,(client, data, ackSender)->{
            //处理事件
            ackSender.sendAckData("success");
            //广播所有人
            socketIOServer.getBroadcastOperations().sendEvent("chat",data);
        });
    }
}
