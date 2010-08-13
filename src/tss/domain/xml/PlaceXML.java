package tss.domain.xml;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import tss.domain.*;

import java.io.File;

public class PlaceXML extends TssXMLReader {
                   
    public PlaceXML (){
        super(DefaultProperties.getAppConfigBase() + File.separator + DefaultProperties.PLACE_XML, DefaultProperties.PLACE_QUALIFIED_NAME,
                                   DefaultProperties.PLACE_FIRST_ELEMENT_NAME, DefaultProperties.PLACE_FIRST_ELEMENT_ATTRIBUTES);
    }    
}