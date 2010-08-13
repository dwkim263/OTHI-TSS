package tss.service;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.xml.TopicEvaluationXML;

public class TopicEvaluationManager implements Serializable{

    protected final Log logger = LogFactory.getLog(getClass());
    
    private TopicEvaluationXML topicEvaluationXML;
 
    public void createTopicEvaluationXML
            (String courseId, String topicTitle, String topicSampleAnswer,
             String topicConclusion, String treasureClue) {
        
        // courseId is directoryName        
        topicEvaluationXML = new TopicEvaluationXML
                (courseId, topicTitle, topicSampleAnswer, topicConclusion, treasureClue);
        
        topicEvaluationXML.putFirstNode(topicTitle);
        topicEvaluationXML.putTextNodes();    
        topicEvaluationXML.writeXML();           
    }
    
    public TopicEvaluationXML getTopicEvaluation() {
        return topicEvaluationXML;
    }               
}
