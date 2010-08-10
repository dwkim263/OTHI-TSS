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

public class SubTopicSave {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private String courseId;
    private String topicTitle;    
    private String subTopicTitle;    
    private String subTopicDescription;
    private String subTopicClue;

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
    
    public String getCourseId() {
        return courseId;
    }

    public String getSubTopicClue() {
        return subTopicClue;
    }

    public String getSubTopicDescription() {
        return subTopicDescription;
    }

    public String getSubTopicTitle() {
        return subTopicTitle;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setSubTopicClue(String subTopicClue) {
        this.subTopicClue = subTopicClue;
    }

    public void setSubTopicDescription(String subTopicDescription) {
        this.subTopicDescription = subTopicDescription;
    }

    public void setSubTopicTitle(String subTopicTitle) {
        this.subTopicTitle = subTopicTitle;
    }    
}
