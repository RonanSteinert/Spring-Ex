package co.develhope.NoSQL_ORM.repositories;

import co.develhope.NoSQL_ORM.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
