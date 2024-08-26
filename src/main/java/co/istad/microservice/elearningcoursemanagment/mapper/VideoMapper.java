package co.istad.microservice.elearningcoursemanagment.mapper;


import co.istad.microservice.elearningcoursemanagment.domain.Video;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.UpdateVideo;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.VideoRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    Video videoRequestToVideo(VideoRequest videoRequest);
    Video updateVideo(UpdateVideo updateVideo);
}
