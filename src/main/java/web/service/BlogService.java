package web.service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface BlogService {
    Map<String,Object> getInfoByBlogId(Integer id);

    ArrayList<HashMap> categoryList(HttpServletRequest request);

    HashMap blogList(HttpServletRequest request);

    int editBlog(HashMap params) throws Exception;
}
