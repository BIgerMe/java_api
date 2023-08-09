package web.service.serviceImpl;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entity.TUser;
import web.entity.room.TRoom;
import web.entity.room.TRoomContent;
import web.entity.room.TRoomUser;
import web.repository.room.RoomContentRepository;
import web.repository.room.RoomRepository;
import web.repository.room.RoomUserRepository;
import web.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository RoomRepository;
    @Autowired
    private RoomUserRepository RoomUserRepository;
    @Autowired
    private RoomContentRepository RoomContentRepository;

    public void roomAdd(HttpServletRequest request,HashMap params){

        TUser user = (TUser) request.getAttribute("user");
        //房间新建
        TRoom room = new TRoom();
        room.setTitle((String) params.get("title"));
        room.setUserId(user.getId());
        RoomRepository.save(room);
        //房间用户新增
        TRoomUser roomUser = new TRoomUser();
        roomUser.setRoomId(room.getId());
        roomUser.setUserId(user.getId());
        roomUser.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        RoomUserRepository.save(roomUser);
    }

    public void roomAddTo(HttpServletRequest request,HashMap params){

        TUser user = (TUser) request.getAttribute("user");

        //房间用户新增
        TRoomUser roomUser = new TRoomUser();
        roomUser.setRoomId(Integer.parseInt((String) params.get("room")));
        roomUser.setUserId(user.getId());
        roomUser.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        RoomUserRepository.save(roomUser);
    }

    public HashMap roomList(HttpServletRequest request){
        TUser user = (TUser) request.getAttribute("user");

        ArrayList<Map<String,Object>> data = RoomUserRepository.getRoomByUserID(user.getId());

        HashMap ret = new HashMap();
        for(Map<String,Object> roomData : data){
            HashMap<String,Object> room = new HashMap<>();
            for(String key: roomData.keySet()){
                room.put(key,roomData.get(key));
            }
            ArrayList<TRoomContent> content = RoomContentRepository.getAllByRoomIdOrderByCreateAtAsc((int)roomData.get("room_id"));
            room.put("content",content);
            ret.put(room.get("room_id"),room);
        }
        return ret;
    }

    public void contentAdd(JsonObject jsonObject,TUser user){
        String room = jsonObject.get("room").getAsString();
        String content = jsonObject.get("content").getAsString();
        Integer type = jsonObject.get("type").getAsInt();

        TRoomContent roomContent = new TRoomContent();
        roomContent.setType(type);
        roomContent.setStatus(0);
        roomContent.setRoomId(Integer.valueOf(room));
        roomContent.setContent(content);
        roomContent.setUserId(user.getId());
        roomContent.setNickname(user.getNickname());
        roomContent.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        RoomContentRepository.save(roomContent);
    }
}
