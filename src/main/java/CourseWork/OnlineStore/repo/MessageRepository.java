package CourseWork.OnlineStore.repo;

import CourseWork.OnlineStore.models.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

//    List<Message> find(String tag);

}
