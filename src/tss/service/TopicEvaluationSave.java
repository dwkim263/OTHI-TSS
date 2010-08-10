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

public class TopicEvaluationSave {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private String courseId;
    private String courseTitle;
    private String topicTitle;        
    private String topicQuestion;    
    private String topicSampleAnswer;
    private String topicConclusion;
    private String treasureClue;

    public String getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public String getTopicConclusion() {
        return topicConclusion;
    }

    public String getTopicQuestion() {
        return topicQuestion;
    }

    public String getTopicSampleAnswer() {
        return topicSampleAnswer;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public void setTopicConclusion(String topicConclusion) {
        this.topicConclusion = topicConclusion;
    }

    public void setTopicQuestion(String topicQuestion) {
        this.topicQuestion = topicQuestion;
    }

    public void setTopicSampleAnswer(String topicSampleAnswer) {
        this.topicSampleAnswer = topicSampleAnswer;
    }

    public String getTreasureClue() {
        return treasureClue;
    }

    public void setTreasureClue(String treasureClue) {
        this.treasureClue = treasureClue;
    }
}
