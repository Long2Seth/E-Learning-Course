package co.istad.microservice.elearningcoursemanagment.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Video {
    private Integer orderNo;
    private String lectureNo;
    private String title;
    private String fileName;
}
