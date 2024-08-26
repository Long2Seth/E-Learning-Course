package co.istad.microservice.elearningcoursemanagment.mapper;


import co.istad.microservice.elearningcoursemanagment.domain.Section;
import co.istad.microservice.elearningcoursemanagment.domain.Video;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.SectionRequest;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.VideoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SectionMapper {


//    @Mapping(target = "videos", source = "videos")
    Section toSection(SectionRequest sectionRequest);



}
