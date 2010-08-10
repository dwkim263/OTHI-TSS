package tss.service;

/**
 *
 * @author Dong Won Kim
 */
import tss.domain.Course;
import tss.domain.Topic;

public class SelectingTopic {
    
    private String courseId;

    private Course course;
    
    private Topic topic;
        
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
            
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }       
}
