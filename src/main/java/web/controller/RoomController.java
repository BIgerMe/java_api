package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.service.RoomService;
import web.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService RoomService;

    @PostMapping("/add")
    public Result add(@RequestBody HashMap params,HttpServletRequest request){
        try{
            RoomService.roomAdd(request,params);
            return Result.success();
        }catch (Exception e) {
            return Result.error(201,e.getMessage());
        }
    }

    @PostMapping("/addto")
    public Result addTo(@RequestBody HashMap params,HttpServletRequest request){
        try{
            RoomService.roomAddTo(request,params);
            return Result.success();
        }catch (Exception e) {
            return Result.error(201,e.getMessage());
        }
    }

    @GetMapping("/list")
    public Result<HashMap> list(HttpServletRequest request){
        HashMap data = RoomService.roomList(request);
        return Result.success(data);
    }

}
