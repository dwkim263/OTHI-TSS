/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.service;

/**
 *
 * @author Steve
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TopicOrientationSave {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private String courseId;
    private String topicTitle;    
    private String topicIntroduction;
    private String topicQuestion;

    public void setTopicIntroduction(String topicIntroduction) {
        this.topicIntroduction = topicIntroduction;
    }

    public void setTopicQuestion(String topicQuestion) {
        this.topicQuestion = topicQuestion;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicIntroduction() {
        return topicIntroduction;
    }

    public String getTopicQuestion() {
        return topicQuestion;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }    
}
