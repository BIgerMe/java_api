package web.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entity.blog.TBlogComment;

@Repository
public interface BlogCommentRepository extends JpaRepository<TBlogComment,Long> {

}
