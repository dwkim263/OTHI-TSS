package tss.service;

/**
 *
 * @author Dong Won Kim
 */
import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.xml.TopicOrientationXML;

public class TopicOrientationManager implements Serializable{

    protected final Log logger = LogFactory.getLog(getClass());
            
    private TopicOrientationXML topicOrientationXML;
 
    public void createTopicOrientationXML
            (String courseId, String topicTitle, String topicIntroduction, String topicQuestion) {
        
        // courseId is directoryName        
        topicOrientationXML = new TopicOrientationXML
                (courseId, topicTitle, topicIntroduction, topicQuestion);
        
        topicOrientationXML.putFirstNode(topicTitle);
        topicOrientationXML.putTextNodes();    
        topicOrientationXML.writeXML();           
    }
    
    public TopicOrientationXML getTopicOrientation() {
        return topicOrientationXML;
    }
}
