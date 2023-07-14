package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entity.TUser;

@Repository
public interface UserRepository extends JpaRepository<TUser,Long> {
    TUser findFirstByUsername(String username);
    TUser findFirstByTokenAndTokenExpireAfter(String token,String tokenExpire);
}
