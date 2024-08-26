package co.istad.microservice.elearningcoursemanagment.feature.category;

import co.istad.microservice.elearningcoursemanagment.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

    Optional<Category> findByName(String name);


}
