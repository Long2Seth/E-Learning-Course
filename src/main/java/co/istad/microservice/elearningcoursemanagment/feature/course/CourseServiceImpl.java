package co.istad.microservice.elearningcoursemanagment.feature.course;

import co.istad.microservice.elearningcoursemanagment.domain.Category;
import co.istad.microservice.elearningcoursemanagment.domain.Course;
import co.istad.microservice.elearningcoursemanagment.domain.Section;
import co.istad.microservice.elearningcoursemanagment.domain.Video;
import co.istad.microservice.elearningcoursemanagment.feature.category.CategoryRepository;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.*;
import co.istad.microservice.elearningcoursemanagment.feature.section.SectionRepository;
import co.istad.microservice.elearningcoursemanagment.mapper.CourseMapper;
import co.istad.microservice.elearningcoursemanagment.mapper.SectionMapper;
import co.istad.microservice.elearningcoursemanagment.mapper.VideoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final CourseMapper courseMapper;
    private final SectionMapper sectionMapper;
    private final SectionRepository sectionRepository;
    private final VideoMapper videoMapper;

    @Override
    public void createCourse(CourseRequest courseRequest) {

        if(courseRequest.categoryName() == null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name is required");
        }

        Optional<Category> category = categoryRepository.findByName(courseRequest.categoryName());
        if (category.isEmpty() || category == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Category name = " + courseRequest.categoryName() + " not found");
        }

        Course course = courseMapper.courseRequestToCourse(courseRequest);
        course.setUuid(UUID.randomUUID().toString());
        course.setCategory(category.get());
        course.setIsDeleted(false);
        course.setIsDrafted(false);
        course.setIsPaid(false);
        course.setUpdatedAt( LocalDateTime.now());
        courseRepository.save(course);

    }


    @Override
    public void createSectionCourse(String courseId , SectionRequest sectionRequest) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND , String.format("Course id = %s not found . " , courseId))
                );

        Section section = sectionMapper.toSection(sectionRequest);
        course.getSections().add(section);
        courseRepository.save(course);

    }



    @Override
    public void createVideoSection(String courseId, VideoRequest videoRequest) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Course id = %s not found.", courseId)));

        // Find the section by its order number within the course
        Section section = course.getSections().stream()
                .filter(s -> s.getOrderNo().equals(videoRequest.sectionOrderNo()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Section No = %s not found.", videoRequest.sectionOrderNo())));

        System.out.println(" ORDER NO = " + videoRequest.sectionOrderNo());
        log.info(" ORDER NO = " + videoRequest.sectionOrderNo());

        Video video = videoMapper.videoRequestToVideo(videoRequest);
        section.getVideos().add(video);
        sectionRepository.save(section);
        courseRepository.save(course);

    }

    @Override
        public void updateVideoSection(String courseId, UpdateVideo updateVideo) {

            Course course = courseRepository.findById(courseId)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND , String.format("Course id = %s not found ." , courseId))
                    );

            Section section = course.getSections().stream()
                    .filter(s -> s.getOrderNo().equals(updateVideo.sectionOrderNo()))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Section No = %s not found.", updateVideo.sectionOrderNo())));

        List<Video> videos = section.getVideos();
        if (videos == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No videos found in Section No = %s.", updateVideo.sectionOrderNo()));
        }

        // Find and update the video
        Video existingVideo = videos.stream()
                .filter(v -> v.getOrderNo().equals(updateVideo.orderNo()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Video with orderNo = %s not found in Section No = %s.", updateVideo.orderNo(), updateVideo.sectionOrderNo())));

        existingVideo.setOrderNo(updateVideo.orderNo());
        existingVideo.setTitle(updateVideo.title());
        existingVideo.setFileName(updateVideo.fileName());
        existingVideo.setLectureNo(updateVideo.lectureNo());


        courseRepository.save(course);

        }


    @Override
    public Page<?> getAllCourses(int page, int size, String part) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Course> coursePage = courseRepository.findAll(pageRequest);

        if ("CONTENT_DETAIL".equals(part)) {
            List<CourseResponseDetail> courseResponseDetailList = coursePage.stream()
                    .filter(course -> !course.getIsDeleted() || course.getIsDrafted())
                    .map(courseMapper::courseToCourseResponseDetail)
                    .toList();
            return new PageImpl<>(courseResponseDetailList, pageRequest, coursePage.getTotalElements());
        } else {
            List<CourseResponse> courseResponseList = coursePage.stream()
                    .filter(course -> !course.getIsDeleted() || course.getIsDrafted())
                    .map(courseMapper::courseToCourseResponse)
                    .toList();
            return new PageImpl<>(courseResponseList, pageRequest, coursePage.getTotalElements());
        }
    }

    @Override
    public SlugResponse getAllCoursesSlug( String slug) {

        Course course = courseRepository.findBySlug(slug)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Course slug = " + slug + " not found ")
                );

        return courseMapper.courseToSlugResponse(course);
    }

    @Override
    public Page<CourseResponse> getAllCoursesPrivate( int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        List<Course> course = courseRepository.findAll(pageRequest).toList();
        List<CourseResponse> courseResponseList = course.stream()
                .filter(Course::getIsDeleted)
                .map(courseMapper::courseToCourseResponse).toList();

        return new PageImpl<>(courseResponseList, pageRequest, course.size());

    }

    @Override
    public  Page<CourseResponse> getAllCoursesPublic( int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        List<Course> course = courseRepository.findAll(pageRequest).toList();
        List<CourseResponse> courseResponseList = course.stream()
                .filter(Course -> !Course.getIsDrafted())
                .map(courseMapper::courseToCourseResponse).toList();
        return new PageImpl<>(courseResponseList, pageRequest, course.size());
    }

    @Override
    public Page<CourseResponse> getAllCoursesFree(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        List<Course> course = courseRepository.findAll(pageRequest).toList();
        List<CourseResponse> courseResponseList = course.stream()
                .filter(Course -> Course.getPrice() == 0.0 )
                .map(courseMapper::courseToCourseResponse).toList();
        return new PageImpl<>(courseResponseList, pageRequest, course.size());
    }

    @Override
    public CourseResponse getCourseById(String id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course id = " + id + " not found")
                );
        return courseMapper.courseToCourseResponse(course);

    }

    @Override
    public void updateCourse(CourseRequest courseRequest) {


    }

    @Override
    public void deleteCourseById(String id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course id = " + id + " not found")
                );
        courseRepository.delete(course);
        courseRepository.save(course);

    }

    @Override
    public void updateVisibilityById(String id , UpdateVisibility updateVisibility) {

        Course course = courseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course id = " + id + " not found")
                );
        //Show public course to user
        course.setIsDrafted(updateVisibility.status());
        courseRepository.save(course);

    }

    @Override
    public void updateThumbnailById(String id , UpdateThumbnail updateThumbnail) {

        Course course = courseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course id = " + id + " not found")
                );
        course.setThumbnail(updateThumbnail.thumbnail());
        courseRepository.save(course);

    }

    @Override
    public void updateIsPaidById(String id , UpdateIsPaid updateIsPaid) {

        Course course = courseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course id = " + id + " not found")
                );
        course.setIsPaid(updateIsPaid.status());
        courseRepository.save(course);

    }

    @Override
    public void enableCourseById(String id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course id = " + id + " not found")
                );
        course.setIsDeleted(true);
        courseRepository.save(course);

    }

    @Override
    public void disableCourseById(String id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course id = " + id + " not found")
                );
        course.setIsDeleted(false);
        courseRepository.save(course);

    }


}