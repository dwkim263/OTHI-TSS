package tss.domain.xml;

/**
 *
 * @author Dong Won Kim
 */
import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TssXMLReader extends TssXML {
    
    protected final Log logger = LogFactory.getLog(getClass());       
    
    private String qualifiedName;
    private String firstElementName;
    private String[] firstElementAttributes;

    private Element firstNodeElement = null;
            
    public TssXMLReader (String fileName, String qualifiedName,
                         String firstElementName, String[] firstElementAttributes){
        setXmlFile(new XmlFile());
        getXmlFile().setFileName(fileName);
        getXmlFile().setExisted(true);
        setQualifiedName(qualifiedName);
        setFirstElementName(firstElementName);
        setFirstElementAttributes(firstElementAttributes);
        setXmlDoc(getXmlFile(), qualifiedName);       
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
                    Map<String, Object> property = new HashMap<String, Object>();                    
                    
                    Element documentElement = (Element)documentNode;

                    for (String attribute: firstElementAttributes) {
                        property.put(attribute, documentElement.getAttribute(attribute));
                    }                    
                    properties.put((String) property.get("name"), property);                       
                }
            }
        }
        return properties;
    }

    public Map<String, Object> getObjectProperties(String objectType){

        Map<String, Object> property = new HashMap<String, Object>();

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

                    Element documentElement = (Element)documentNode;

                    String elementName = (String) documentElement.getAttribute("name");

                    List<String> objectList = new LinkedList<String>();

                    NodeList nodes = getNamedNode(documentElement, objectType);

                    if (nodes != null) {
                        for (int j=0; j < nodes.getLength(); ++j) {
                                Node objectNode = nodes.item(j);
                                if (objectNode.getNodeType() == Node.ELEMENT_NODE){
                                    Element objectElement = (Element)objectNode;
                                    String name = objectElement.getAttribute("name");
                                    objectList.add(name);
                                }
                        }

                    }

                    property.put(elementName, objectList);
                }
            }
        }
        return property;
    }

    public String[] getFirstElementAttributes() {
        return firstElementAttributes;
    }

    public void setFirstElementAttributes(String[] firstElementAttributes) {
        this.firstElementAttributes = firstElementAttributes;
    }

    public String getFirstElementName() {
        return firstElementName;
    }

    public void setFirstElementName(String firstElementName) {
        this.firstElementName = firstElementName;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }
}
