package tss.service;

/**
 *
 * @author Dong Won (Steven) Kim
 */

public class Selectings {
    
    private String courseId = null;
    
    private String courseTitle = null;    
    
    private String topicTitle = null;
    
    private String topicQuestion =null;

    public String getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getTopicQuestion() {
        return topicQuestion;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setTopicQuestion(String topicQuestion) {
        this.topicQuestion = topicQuestion;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
    
}
