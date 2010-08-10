package tss.domain.xml;

/**
 *
 * @author Dong Won Kim
 */
import tss.domain.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import org.w3c.dom.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TopicEvaluationXML extends TssXML {
    
    protected final Log logger = LogFactory.getLog(getClass());       
    
    private final String qualifiedName = "evaluation";
    private final String firstElementName = "topic";    
    private final String[] firstElementAttributes = new String[] {"title"};        
    private final String[] textElementNames = new String[]{"sample_answer", "conclusion", "clue"};

    private String[] textNodeValues = new String[3];
    private Element firstNodeElement = null;
            
    public TopicEvaluationXML (String xmlFileName){     
        setXmlFile(new XmlFile());
        getXmlFile().setFileName(xmlFileName);
        getXmlFile().setExisted(xmlFileName);        
       if (getXmlFile().isExisted()){
            setXmlDoc(getXmlFile(), qualifiedName);
       }
    }
        
    public TopicEvaluationXML 
            (String courseId, String topicTitle, String topicSampleAnswer,
             String topicConclusion, String treasureClue) {
         
        setTextNodeValues(topicSampleAnswer, topicConclusion, treasureClue);
        setXmlFile(new XmlFile(DefaultProperties.getAppRoot() + File.separator + courseId + File.separator + qualifiedName + ".xml"));
        if (getXmlFile().isExisted()){
            setXmlDoc(getXmlFile(), qualifiedName);
        } else {
            createXmlDoc(qualifiedName);        
        }    
    }
    
    public void putFirstNode(String attributeValue){
        Element root = getXmlDoc().getDocumentElement(); 
        String[] attributeValues = new String[1];
        attributeValues[0] = attributeValue;
        firstNodeElement = putAttributeNode(getXmlDoc(), root, firstElementName, firstElementAttributes, attributeValues);        
    }
    
    public void putTextNodes(){
        putTextNodes(getXmlDoc(), getFirstNodeElement(), textElementNames, textNodeValues);
    }
            
    public void writeXML(){
        writeXML(getXmlFile());
    }
    
    public void setTextNodeValues (String topicSampleAnswer,
                String topicConclusion, String treasureClue){
        textNodeValues[0] = topicSampleAnswer;   
        textNodeValues[1] = topicConclusion;
        textNodeValues[2] = treasureClue;
    }

    public Element getFirstNodeElement() {
        return firstNodeElement;
    }
    
    public Map<String, Object> getProperties(){

        Map<String, Object> properties = new HashMap<String, Object>();
        
        if (getXmlDoc() != null) {
            Element element = getXmlDoc().getDocumentElement();
            if (!element.getNodeName().equals(qualifiedName)) {
                try {
                    throw new IOException("Not a document file");
                } catch (IOException ex) {
                    logger.info(ex);
                }
            }

            NodeList documentlist = element.getElementsByTagName(firstElementName);
            for (int i = 0; i < documentlist.getLength(); i++) {
                Node documentNode = documentlist.item(i);
                if (documentNode.getNodeType() == Node.ELEMENT_NODE){    
                    
                    Map<String, String> property = new HashMap<String, String>();
                    
                    Element documentElement = (Element)documentNode;

                    for (String attribute: firstElementAttributes) {
                        property.put(attribute, documentElement.getAttribute(attribute));
                    }

                    for (String elementName: textElementNames) {
                        property.put(elementName, getFistNodeValue(documentElement, elementName));
                    }
                    
                    properties.put(property.get("title"), property);                    
                }
            }
        }
        
        return properties;            
    }         
}
