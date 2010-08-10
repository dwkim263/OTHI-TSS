package tss.domain.xml;

/**
 *
 * @author Dong Won Kim
 */
import tss.domain.*;

public class ArmorXML extends TssXMLReader {
                   
    public ArmorXML (String xmlFileName){
        super(xmlFileName, DefaultProperties.ARMOR_QUALIFIED_NAME,
                                   DefaultProperties.ARMOR_FIRST_ELEMENT_NAME, DefaultProperties.ARMOR_FIRST_ELEMENT_ATTRIBUTES);
    }    
}