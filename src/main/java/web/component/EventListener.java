package web.component;

import com.corundumstudio.socketio.SocketIOServer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.entity.TUser;
import web.service.RoomService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class EventListener {
    private final SocketIOServer socketIOServer;

    @Autowired
    private RoomService RoomService;
    @Autowired
    public EventListener(SocketIOServer socketIOServer){
        this.socketIOServer = socketIOServer;
        registerEventHandlers();
    }

    public void registerEventHandlers(){
        socketIOServer.addEventListener("joinRoom",String.class,(client,room,ackSender)->{
            client.joinRoom(room);
            ackSender.sendAckData("success");
        });

        socketIOServer.addEventListener("leaveRoom", String.class, (client, room, ackSender) -> {
            // 让客户端离开房间
            client.leaveRoom(room);
            ackSender.sendAckData("success");
        });

        socketIOServer.addEventListener("chat", Object.class,(client, data, ackSender)->{
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(gson.toJson(data),JsonObject.class);
            String room = jsonObject.get("room").getAsString();
            String message = jsonObject.get("content").getAsString();

            System.out.println("收到来自房间 " + room + " 的消息：" + message);
            TUser userInfo = client.get("user");
            jsonObject.addProperty("userId",userInfo.getId());
            jsonObject.addProperty("nickname",userInfo.getNickname());

            LocalDateTime dateTime = LocalDateTime.now(); // 获取当前日期和时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            jsonObject.addProperty("createAt", dateTime.format(formatter));

            RoomService.contentAdd(jsonObject,userInfo);
            Object objectData = gson.fromJson(jsonObject,Object.class);
            //处理事件
            ackSender.sendAckData("success");
            //将事件发送给房间内的所有人
            socketIOServer.getRoomOperations(room).sendEvent("chat",objectData);
        });
    }
}
