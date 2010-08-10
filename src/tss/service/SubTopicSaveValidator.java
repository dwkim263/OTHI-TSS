/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.service;

/**
 *
 * @author Steve
 */
import java.io.File;
import java.util.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.xml.SubTopicXML;
import tss.domain.DefaultProperties;

public class SubTopicSaveValidator implements Validator {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return SubTopicSave.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        SubTopicSave sts = (SubTopicSave) obj;
        if (sts.getSubTopicTitle() == null) {
            errors.rejectValue("subTopicTitle", "error.not-specified", null, "Value required.");
        } else if (sts.getSubTopicDescription() == null) {
            errors.rejectValue("subTopicDescription", "error.not-specified", null, "Value required.");            
        } else if (sts.getSubTopicClue() == null) {
            errors.rejectValue("subTopicClue", "error.not-specified", null, "Value required.");            
        }else {
            SubTopicXML sXml = new SubTopicXML(
                    DefaultProperties.getAppRoot() + File.separator + sts.getCourseId() + File.separator + DefaultProperties.SUB_TOPIC_XML);
            
            if (sXml.getXmlFile().isExisted()) {
                
                boolean sameTitle = false;
                
                Map<String, Object> properties = sXml.getProperties();
                Iterator it = properties.keySet().iterator();
                while (it.hasNext()) {
                    String title = (String) it.next();
                    int indexOfColon = title.indexOf(":");
                    String topicTitle = title.substring(0, indexOfColon);
                    String subTitle = title.substring(indexOfColon + 1);
                    if ( topicTitle.equals(sts.getTopicTitle()) && subTitle.equals(sts.getSubTopicTitle())) sameTitle = true;
                }
                
                if (sameTitle) {
                    errors.rejectValue("subTopicTitle", "error.exist", null, "Value already created.");                  
                }
            }   
        }
    }
}