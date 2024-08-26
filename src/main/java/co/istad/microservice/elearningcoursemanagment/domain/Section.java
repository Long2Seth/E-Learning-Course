package co.istad.microservice.elearningcoursemanagment.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Section {

    private String title;
    private Integer orderNo;
    @Builder.Default
    private List<Video> videos = new ArrayList<>();
}
