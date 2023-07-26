package web.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entity.TUser;
import web.entity.blog.TBlogContent;
import web.entity.blog.TBlogInfo;
import web.holder.UserHolder;
import web.repository.blog.BlogCommentRepository;
import web.repository.blog.BlogContentRepository;
import web.repository.blog.BlogInfoRepository;
import web.service.BlogService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogCommentRepository BlogCommentRepository;
    @Autowired
    private BlogInfoRepository BlogInfoRepository;
    @Autowired
    private BlogContentRepository BlogContentRepository;

    @Override
    public Map<String,Object> getInfoByBlogId(Integer id){

        Map<String,Object> res = BlogInfoRepository.getInfoByBlogId(id);
        return res;
    }

    public ArrayList<HashMap> categoryList(HttpServletRequest request){
        String[] category;
        if(request.getParameter("type").equals("公开")){
            category = BlogInfoRepository.findCategory();
        }else{
            TUser user = (TUser) request.getAttribute("user");
            category = BlogInfoRepository.findCategory(user.getId());
        }


        Map<String,Integer> map = new HashMap<>();
        for(String item : category){
            String[] split = item.split(",");
            for(String ck : split){
                if(map.containsKey(ck)){
                    map.put(ck,(int)map.get(ck)+1);
                }else{
                    map.put(ck,1);
                }
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        ArrayList<HashMap> categoryList = new ArrayList<>();

        for(Map.Entry<String,Integer> entry : list){
            HashMap categoryOne = new HashMap();
            categoryOne.put("name",entry.getKey());
            categoryOne.put("value",entry.getValue());
            categoryList.add(categoryOne);
        }
        return categoryList;
    }

    public HashMap blogList(HttpServletRequest request){
        HashMap data  = new HashMap();
        String search = request.getParameter("search");
        int offset = (Integer.parseInt(request.getParameter("page"))- 1) * 20;

        int total;
        ArrayList<TBlogInfo> blogList;
        if(request.getParameter("type").equals("公开")){
            total = BlogInfoRepository.blogCount(search);
            blogList = BlogInfoRepository.blogList(offset,search);
        }else{
            TUser user = (TUser) request.getAttribute("user");
            total = BlogInfoRepository.blogCount(search,user.getId());
            blogList = BlogInfoRepository.blogList(offset,search,user.getId());
        }


        ArrayList<HashMap> blogArrayList = new ArrayList<>();
        for(TBlogInfo blog: blogList){
            HashMap hashMap = new HashMap<>();
            hashMap.put("id",blog.getId());
            hashMap.put("title",blog.getTitle());
            hashMap.put("publish",blog.getPublish());
            hashMap.put("category",blog.getCategory().split(","));
            blogArrayList.add(hashMap);
        }

        data.put("total",total);
        data.put("data",blogArrayList);
        return data;
    }

    @Transactional
    public int editBlog(HashMap params) throws Exception {

        if(params.containsKey("id")){ //修改
            TBlogInfo blog = BlogInfoRepository.findFirstById((Integer) params.get("id"));
            if(blog != null){
                blog.setTitle((String) params.get("title"));
                blog.setCategory((String) params.get("category"));
                blog.setPublish((String) params.get("publish"));
                BlogInfoRepository.save(blog);

                TBlogContent blogContent = BlogContentRepository.findFirstByBlogId(blog.getId());
                if(blogContent != null){
                    blogContent.setContent((String) params.get("content"));
                    BlogContentRepository.save(blogContent);
                }

                return blog.getId();
            }else{
                throw new Exception("不存在！");
            }
        }else{//新增
            TBlogInfo blog = new TBlogInfo();
            TUser user = UserHolder.getUser();
            UserHolder.clearUser();
            blog.setUserId(user.getId());
            blog.setTitle((String) params.get("title"));
            blog.setCategory((String) params.get("category"));
            blog.setPublish((String) params.get("publish"));
            TBlogInfo saveBlog = BlogInfoRepository.save(blog);

            TBlogContent blogContent = new TBlogContent();
            blogContent.setBlogId(saveBlog.getId());
            blogContent.setContent((String) params.get("content"));
            BlogContentRepository.save(blogContent);
            return saveBlog.getId();
        }

    }
}
