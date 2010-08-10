package tss.domain;

/**
 *
 * @author Steve
 */
import tss.domain.xml.SubTopicXML;
import tss.domain.xml.CourseIntroductionXML;
import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class SubTopic {
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static Map<String, SubTopic> subTopics = new HashMap<String, SubTopic>();

    private String courseId;
    private String courseTitle;
    private String topicTitle;
    private String subTopicTitle;
    private String subTopicDescription;
    private String subTopicClue;    
        
    private SubTopic(String courseId, String courseTitle, String topicTitle, 
            String subTopicTitle, String subTopicDescription, String subTopicClue){
        
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.topicTitle = topicTitle;
        this.subTopicTitle = subTopicTitle;
        this.subTopicDescription = subTopicDescription;
        this.subTopicClue = subTopicClue;        
        subTopics.put(courseId + ":" + topicTitle + ":" + subTopicTitle, this);
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
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

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
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
      

    public static Map<String, SubTopic> getSubTopics(String courseId) {
        setTopics(courseId);
        return subTopics;
    }
    
    public static void setTopics(String courseId) {               
        CourseIntroductionXML introductionXml = null;
        SubTopicXML subTopicXml = null;

        File[] files = (new File(DefaultProperties.getAppRoot() + File.separator + File.separator + courseId)).listFiles();
        for (File file: files) {
            if (file.getName().equals(DefaultProperties.INTRODUCTION_XML)) {
                introductionXml = new CourseIntroductionXML(file.getAbsolutePath());
            } else if (file.getName().equals(DefaultProperties.SUB_TOPIC_XML)) {
                subTopicXml = new SubTopicXML(file.getAbsolutePath());
            }
        }

        if (introductionXml != null && subTopicXml != null) {
            Map <String, Object> introduction = introductionXml.getProperties();
            Map <String, Object> subTopicMap = subTopicXml.getProperties();

            Iterator it = subTopicMap.keySet().iterator();
            while(it.hasNext()) {
                String title = (String) it.next();
                int indexOfColon = title.indexOf(":");
                String mainTopicTitle = title.substring(0, indexOfColon);
                String subTopicTitle = title.substring(indexOfColon + 1);
                Map <String, String> subTopic = (Map <String, String>) subTopicMap.get(title);
                new SubTopic((String) introduction.get("id"), (String) introduction.get("title"),
                     mainTopicTitle, subTopicTitle, subTopic.get("description"), subTopic.get("clue") );
            }
        }
    }
      
    public static void setTopics(Map<String, SubTopic> subTopic) {
        SubTopic.subTopics = subTopic;
    }
    
   
    public static SubTopic getById(String id) {
        return subTopics.get(id);
    }        
}
