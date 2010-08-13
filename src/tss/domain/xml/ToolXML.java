package tss.domain.xml;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import tss.domain.*;

public class ToolXML extends TssXMLReader {
                   
    public ToolXML (String xmlFileName){
        super(xmlFileName, DefaultProperties.TOOL_QUALIFIED_NAME,
                                   DefaultProperties.TOOL_FIRST_ELEMENT_NAME, DefaultProperties.TOOL_FIRST_ELEMENT_ATTRIBUTES);
    }    
}