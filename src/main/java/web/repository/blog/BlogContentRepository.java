package web.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entity.blog.TBlogContent;

@Repository
public interface BlogContentRepository extends JpaRepository<TBlogContent,Long> {
    TBlogContent findFirstByBlogId(int blog_id);
}
