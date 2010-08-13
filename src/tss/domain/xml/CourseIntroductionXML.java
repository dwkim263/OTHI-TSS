package tss.domain.xml;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import tss.domain.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import org.w3c.dom.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CourseIntroductionXML extends TssXML {
    
    protected final Log logger = LogFactory.getLog(getClass());       
    
    private final String qualifiedName = "introduction";
    private final String[] textElementNames = new String[]{"id", "title", "objective", "references"};

    private String[] textNodeValues = new String[4];

    public CourseIntroductionXML (String xmlFileName){       
        setXmlFile(new XmlFile());
        getXmlFile().setFileName(xmlFileName);
        getXmlFile().setExisted(xmlFileName);
       if (getXmlFile().isExisted()){
            setXmlDoc(getXmlFile(), qualifiedName);
       }                
    }
    
    public CourseIntroductionXML 
            (String courseId, String courseTitle, String courseObjective, String courseReferences) {
        
        setTextNodeValues(courseId, courseTitle, courseObjective, courseReferences);
        setXmlFile(new XmlFile(DefaultProperties.getAppRoot() + File.separator + courseId + File.separator + qualifiedName + ".xml"));
        if (!getXmlFile().isExisted()){
            createXmlDoc(qualifiedName);
        }
    }
    
    public void putTextNodes() {
        Element element = getXmlDoc().getDocumentElement();            
        putTextNodes(getXmlDoc(), element, textElementNames, textNodeValues);
    }
            
    public void writeXML(){
        writeXML(getXmlFile());
    }
    
    public void setTextNodeValues
            (String courseId, String courseTitle, String courseObjective, String courseReferences){
        textNodeValues[0] = courseId;
        textNodeValues[1] = courseTitle;   
        textNodeValues[2] = courseObjective;   
        textNodeValues[3] = courseReferences;           
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

            for (String elementName : textElementNames) {
                 properties.put(elementName, getFistNodeValue(element, elementName));            
            }
        }

        return properties;
    }    
}
