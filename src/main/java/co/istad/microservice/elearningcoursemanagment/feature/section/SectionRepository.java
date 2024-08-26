package co.istad.microservice.elearningcoursemanagment.feature.section;

import co.istad.microservice.elearningcoursemanagment.domain.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SectionRepository extends MongoRepository<Section,String> {

    Optional<Section> findByOrderNo( Integer orderNo);
}
