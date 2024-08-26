package co.istad.microservice.elearningcoursemanagment.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "courses")
@Getter
@Setter
@Data
public class Course {

    @Id
    private String id;
    private String uuid;

    private  String title;
    private  String slug;
    private  String description;
    private  String thumbnail;
    private  double price;
    private double discount;
    private  String content;

    private Boolean isPaid;
    private Boolean isDrafted;
    private Boolean isDeleted;

    List<Section> sections = new ArrayList<>();
    @DBRef
    private Category category;

    private String instructorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
