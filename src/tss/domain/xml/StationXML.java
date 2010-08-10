package tss.domain.xml;

/**
 *
 * @author Dong Won Kim
 */
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import org.w3c.dom.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.DefaultProperties;

public class StationXML extends TssXML {
    
    protected final Log logger = LogFactory.getLog(getClass());       
    
    private final String qualifiedName = "stations";
    private final String firstElementName = "station";
    private final String[] firstElementAttributes = new String[] {"topic", "name", "place", "place_nick_name"};

    private Element firstNodeElement = null;
    private String[] textElementNames;

    public StationXML (String xmlFileName){
        setXmlFile(new XmlFile());
        getXmlFile().setFileName(xmlFileName);
        getXmlFile().setExisted(xmlFileName);        
       if (getXmlFile().isExisted()){
            setXmlDoc(getXmlFile(), qualifiedName);
       }
    }

    public StationXML(String courseId, String[] textElementNames) {
        setTextElementNames(textElementNames);
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

    public void putFirstNode(String[] attributeValues){
        Element root = getXmlDoc().getDocumentElement();
        firstNodeElement = putAttributeNode(getXmlDoc(), root, firstElementName, firstElementAttributes, attributeValues);
    }
    
    public void putTextNodes(String[] textNodeValues){
        putTextNodes(getXmlDoc(), getFirstNodeElement(), getTextElementNames(), textNodeValues);
    }
            
    public void writeXML(){
        writeXML(getXmlFile());
    }

    public Element getFirstNodeElement() {
        return firstNodeElement;
    }

    public void setFirstNodeElement(Element firstNodeElement) {
        this.firstNodeElement = firstNodeElement;
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

                    if (getTextElementNames() != null) {
                        for (String elementName: getTextElementNames()) {
                            property.put(elementName, getFistNodeValue(documentElement, elementName));
                        }
                    }
                    
                    properties.put(property.get("topic") + ":" + property.get("name"), property);
                }
            }
        }
        
        return properties;            
    }

    public String[] getTextElementNames() {
        return textElementNames;
    }

    public void setTextElementNames(String[] textElementNames) {
        this.textElementNames = textElementNames;
    }

    public String[] getFirstElementAttributes() {
        return firstElementAttributes;
    }

    public String getFirstElementName() {
        return firstElementName;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }
}
