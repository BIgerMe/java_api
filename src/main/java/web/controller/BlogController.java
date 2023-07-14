package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.service.BlogService;
import web.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService BlogService;

    @GetMapping("/detail")
    public Result<Map<String,Object>> detail(@RequestParam Integer id){
        return Result.success(BlogService.getInfoByBlogId(id));
    }

    @GetMapping("/category")
    public Result<ArrayList> categoryList(HttpServletRequest request){
        return Result.success(BlogService.categoryList(request));
    }

    @GetMapping("/list")
    public Result<HashMap> list(HttpServletRequest request){
        return Result.success(BlogService.blogList(request));
    }

    @PostMapping("/edit")
    public Result login(@RequestBody HashMap params){
        try{
            int id = BlogService.editBlog(params);
            return Result.success(id);
        }catch (Exception ex){
            return Result.error(-1,ex.getMessage());
        }
    }
}
