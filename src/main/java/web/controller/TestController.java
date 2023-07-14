package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.entity.TTestEntity;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TTestEntity TTestEntity;

    @GetMapping("/test")
    public TTestEntity test(){
        return TTestEntity;
    }

}
