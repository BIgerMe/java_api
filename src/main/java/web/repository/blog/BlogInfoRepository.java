package web.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.entity.blog.TBlogInfo;


import java.util.ArrayList;
import java.util.Map;

@Repository
public interface BlogInfoRepository extends JpaRepository<TBlogInfo,Long> {

//    TBlogInfo
    TBlogInfo findFirstById(int id);
    @Query(value = "select t0.*,t1.content from t_blog_info t0 left join t_blog_content t1 on t0.id=t1.blog_id where t0.id =?1",nativeQuery = true)
    Map<String,Object> getInfoByBlogId(Integer id);

    /**公开*/
    @Query(value="select category from t_blog_info where publish = '公开'",nativeQuery = true)
    String[] findCategory();
    @Query(value="select * from t_blog_info where publish = '公开' and (title like %:search% or category like %:search%) order by id desc limit :offset,20",nativeQuery = true)
    ArrayList<TBlogInfo> blogList(int offset,String search);

    @Query(value="select count(*) from t_blog_info where publish = '公开' and (title like %:search% or category like %:search%) ",nativeQuery = true)
    Integer blogCount(String search);

    /**私有*/
    @Query(value="select category from t_blog_info where user_id = :user_id",nativeQuery = true)
    String[] findCategory(Integer user_id);

    @Query(value="select * from t_blog_info where user_id = :user_id and (title like %:search% or category like %:search% or publish like %:search%) order by id desc limit :offset,20",nativeQuery = true)
    ArrayList<TBlogInfo> blogList(int offset,String search,int user_id);

    @Query(value="select count(*) from t_blog_info where user_id = :user_id and (title like %:search% or category like %:search% or publish like %:search%) ",nativeQuery = true)
    Integer blogCount(String search,int user_id);
}
