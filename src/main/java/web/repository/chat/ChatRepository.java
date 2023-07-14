package web.repository.chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.entity.mongodb.Chat;

public interface ChatRepository extends MongoRepository<Chat,String> {
}
