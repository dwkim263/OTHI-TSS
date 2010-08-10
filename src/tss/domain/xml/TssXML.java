package tss.domain.xml;

/**
 *
 * @author Steve
 */
import java.io.*;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*; 
import org.xml.sax.SAXException;

public abstract class TssXML implements Serializable {

    protected final Log logger = LogFactory.getLog(getClass());        

    private Document xmlDoc = null;
    private XmlFile xmlFile = null;    

    public void createXmlDoc(String qualifiedName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation impl = builder.getDOMImplementation();

            xmlDoc = impl.createDocument(null, qualifiedName, null);
        } catch (ParserConfigurationException ex) {
            logger.error("XmlManager" + ex);
        }        
    }   
    
    // public abstract Document buildXML(Document xmldoc, String elementName, String attributeName, String attributeValue);
    public Element putAttributeNode(Document xmlDoc, Element parentElement, String elementName, String[] attributes, String[] attributeValues){
        Element element = xmlDoc.createElementNS(null, elementName);    
        for (int i=0; i<attributes.length; ++i){
            element.setAttributeNS(null, attributes[i], attributeValues[i]);
        }
        parentElement.appendChild(element);
        return element;
    }
    
    public void putTextNodes(Document xmlDoc, Element parentElement, String[] elementNames, String[] textNodeValues) {
        Element element = null;
        Node node = null;   
        
        for (int i=0; i<elementNames.length; ++i){
            element = xmlDoc.createElementNS(null, elementNames[i]);
            node = xmlDoc.createTextNode(textNodeValues[i]);
            element.appendChild(node);
            parentElement.appendChild(element);
        }
    }    
            
    public String getFistNodeValue(Element element, String tagName)
    {
        NodeList list = element.getElementsByTagName(tagName);
        Element cElement = (Element)list.item(0);

        if(cElement != null && cElement.getFirstChild()!=null){
             return cElement.getFirstChild().getNodeValue();
        }else{
            return "";
        }   
    }    
    
    public NodeList getNamedNode(Element element, String name) {
            NodeList list = element.getElementsByTagName(name);
            if (list.getLength() == 0) {
                    return null;
            }
            return list;
    }
    
    public String[] parseParagraphElement(Element element,  String tagName)
    {
        NodeList list = element.getElementsByTagName(tagName);
            
        String[] nodeValues = new String[list.getLength()];
                
        for (int i = 0; i < list.getLength(); i++) {        
            Element cElement = (Element)list.item(i);
         
            if(cElement.getFirstChild()!=null){
                nodeValues[i] = cElement.getFirstChild().getNodeValue();
            }else{
                nodeValues[i] = "";
            }   
        }
        return nodeValues;
    }      
    
    public void writeXML(XmlFile file) {        
        
        DOMSource domSource = new DOMSource(xmlDoc);
        OutputStream outStream = null;
        
        try {
            TransformerFactory transformerFactory  = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            
            outStream = file.getOutStream();

            StreamResult streamResult = new StreamResult(outStream);        
            transformer.transform(domSource, streamResult); 
    
        } catch (TransformerException ex) {
            logger.error("writeXML" + ex);
        } finally {
            //Close the BufferedWriter
            try {
                if (outStream != null) {
                    outStream.flush();
                    outStream.close();
                }
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
    }

    public Document getXmlDoc() {
        return xmlDoc;
    }
    
    public void setXmlDoc(XmlFile xmlFile, String qualifiedName) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(xmlFile.getFileName());

            Element element = document.getDocumentElement();
            if (!element.getNodeName().equals(qualifiedName)) {
                    throw new IOException("Not a " + qualifiedName + "file");
            } else {
               xmlDoc =  document;
            }       
        } catch (ParserConfigurationException ex) {
            logger.error("setXmlDoc" + ex);            
        } catch (SAXException ex) {
            logger.error("setXmlDoc" + ex);                  
        } catch (IOException ex) {
            logger.error("setXmlDoc" + ex);            
        }
    }   
    
    public void setXmlFile(XmlFile xmlFile) {
        this.xmlFile = xmlFile;
    }

    public XmlFile getXmlFile() {
        return xmlFile;
    }    
 
}
