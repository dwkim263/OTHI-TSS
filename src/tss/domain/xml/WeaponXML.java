package tss.domain.xml;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import tss.domain.*;

public class WeaponXML extends TssXMLReader {
                   
    public WeaponXML (String xmlFileName){
        super(xmlFileName, DefaultProperties.WEAPON_QUALIFIED_NAME,
                                   DefaultProperties.WEAPON_FIRST_ELEMENT_NAME, DefaultProperties.WEAPON_FIRST_ELEMENT_ATTRIBUTES);
    }    
}