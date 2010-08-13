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

public class GuideXML extends TssXML {
    
    protected final Log logger = LogFactory.getLog(getClass());       
    
    private final String qualifiedName = "guides";
    private final String firstElementName = "guide";
    private final String[] firstElementAttributes = new String[] {"topic", "place", "name", "nick_name"};
    private final String[] textElementNames = new String[]{"talk", "question", "clue"};

    private String[] textNodeValues = new String[3];
    private Element firstNodeElement = null;
            
    public GuideXML (String xmlFileName){
        setXmlFile(new XmlFile());
        getXmlFile().setFileName(xmlFileName);
        getXmlFile().setExisted(xmlFileName);        
        if (getXmlFile().isExisted()){
            setXmlDoc(getXmlFile(), qualifiedName);
        }
    }
        
    public GuideXML(String courseId, String talk, String question, String clue)
    {
        setTextNodeValues(talk, question, clue);
        setXmlFile(new XmlFile(DefaultProperties.getAppRoot() + File.separator + courseId + File.separator + qualifiedName + ".xml"));
        if (getXmlFile().isExisted()){
            setXmlDoc(getXmlFile(), qualifiedName);
        } else {
            createXmlDoc(qualifiedName);    
        }    
    }

    public void putFirstNode(String[] attributeValues){
        Element root = getXmlDoc().getDocumentElement();
        firstNodeElement = putAttributeNode(getXmlDoc(), root, firstElementName, firstElementAttributes, attributeValues);
    }
    
    public void putTextNodes(){
        putTextNodes(getXmlDoc(), getFirstNodeElement(), textElementNames, textNodeValues);
    }
            
    public void writeXML(){
        writeXML(getXmlFile());
    }
    
    public void setTextNodeValues (String talk, String question, String clue ){
        textNodeValues[0] = talk;
        textNodeValues[1] = question;
        textNodeValues[2] = clue;
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
                    
                    properties.put(property.get("topic") + ":" + property.get("place") + ":" + property.get("name"), property);
                }
            }
        }
        
        return properties;            
    }         
}
