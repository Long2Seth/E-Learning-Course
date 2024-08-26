package co.istad.microservice.elearningcoursemanagment.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@Data
public class Category {

    @Id
    private String id;

    private String uuid;

    private String name;

    private String icon;

    private boolean isDeleted;

}
