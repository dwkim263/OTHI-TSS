package tss.domain;

/**
 *
 * @author Steve
 */
import tss.domain.xml.TopicOrientationXML;
import tss.domain.xml.CourseIntroductionXML;
import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class Topic {
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static Map<String, Topic> topics = new HashMap<String, Topic>();

    private String courseId;
    private String courseTitle;
    private String topicTitle;
    private String topicIntroduction;    
    private String topicQuestion;
    private String topicClue;
    
    private Topic(String courseId, String courseTitle, String topicTitle, 
            String topicIntroduction, String topicQuestion, String topicClue){
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.topicTitle = topicTitle;
        this.topicIntroduction = topicIntroduction;        
        this.topicQuestion = topicQuestion;
        this.topicClue = topicClue;
        topics.put(courseId + ":" + topicTitle, this);
    }
      
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public static Map<String, Topic> getTopics(String courseId) {
        setTopics(courseId);
        return topics;
    }
    
    public static void setTopics(String courseId) {
        CourseIntroductionXML introductionXml = null;
        TopicOrientationXML orientationXml = null;

        File[] files = (new File(DefaultProperties.getAppRoot() + File.separator + File.separator + courseId)).listFiles();
        for (File file: files) {
            if (file.getName().equals(DefaultProperties.INTRODUCTION_XML)) {
                introductionXml = new CourseIntroductionXML(file.getAbsolutePath());
            } else if (file.getName().equals(DefaultProperties.ORIENTATION_XML)) {
                orientationXml = new TopicOrientationXML(file.getAbsolutePath());
            }
        }

        if (introductionXml != null && orientationXml != null) {
            Map <String, Object> introduction = introductionXml.getProperties();
            Map <String, Object> orientation = orientationXml.getProperties();
            Iterator it = orientation.keySet().iterator();
            while(it.hasNext()) {
                String title = (String) it.next();
                Map <String, String> topic = (Map <String, String>) orientation.get(title);
                new Topic((String) introduction.get("id"), (String) introduction.get("title"),
                            title, topic.get("introduction"), topic.get("question"), topic.get("clue") );
            }
        }
    }
      
    public static void setTopics(Map<String, Topic> courses) {
        Topic.topics = courses;
    }

    public static Topic getById(String id) {
        return topics.get(id);
    }

    public String getTopicQuestion() {
        return topicQuestion;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicQuestion(String topicQuestion) {
        this.topicQuestion = topicQuestion;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicClue() {
        return topicClue;
    }

    public void setTopicClue(String topicClue) {
        this.topicClue = topicClue;
    }

    public String getTopicIntroduction() {
        return topicIntroduction;
    }

    public void setTopicIntroduction(String topicIntroduction) {
        this.topicIntroduction = topicIntroduction;
    }
        
}
