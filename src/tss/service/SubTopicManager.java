package tss.service;

/**
 *
 * @author Dong Won Kim
 */
import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.xml.SubTopicXML;

public class SubTopicManager implements Serializable{

    protected final Log logger = LogFactory.getLog(getClass());
    
    private SubTopicXML subTopicXML;
 
    public void createSubTopicXML(String courseId, String topicTitle, String subTopicTitle,
                                  String subTopicDescription, String subTopicClue)
    {
        
        // courseId is directoryName        
        subTopicXML = new SubTopicXML (courseId, subTopicDescription, subTopicClue);
        subTopicXML.putFirstNode(new String[]{topicTitle, subTopicTitle});   
        subTopicXML.putTextNodes();      
        subTopicXML.writeXML();           
    }
    
    public SubTopicXML getSubTopicXML() {
        return subTopicXML;
    }

    public void setSubTopicXML(SubTopicXML subTopicXML) {
        this.subTopicXML = subTopicXML;
    }
}
