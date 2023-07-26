package web.service;

import com.google.gson.JsonObject;
import web.entity.TUser;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface RoomService {
    void roomAdd(HttpServletRequest request,HashMap params);
    void roomAddTo(HttpServletRequest request,HashMap params);
    HashMap roomList(HttpServletRequest request);
    void contentAdd(JsonObject jsonObject, TUser user);
}
