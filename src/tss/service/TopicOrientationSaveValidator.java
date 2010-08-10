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
import tss.domain.xml.TopicOrientationXML;
import tss.domain.DefaultProperties;

public class TopicOrientationSaveValidator implements Validator {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return TopicOrientationSave.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        TopicOrientationSave tos = (TopicOrientationSave) obj;
        if (tos.getTopicTitle() == null) {
            errors.rejectValue("topicTitle", "error.not-specified", null, "Value required.");
        } else if (tos.getTopicIntroduction() == null) {
            errors.rejectValue("topicIntroduction", "error.not-specified", null, "Value required.");            
        } else if (tos.getTopicQuestion() == null) {
            errors.rejectValue("topicQuestion", "error.not-specified", null, "Value required.");            
        } else {
            TopicOrientationXML tXml = new TopicOrientationXML(
                    DefaultProperties.getAppRoot() + File.separator + tos.getCourseId() + File.separator + DefaultProperties.ORIENTATION_XML);
            
            if (tXml.getXmlFile().isExisted()) {
                
                boolean sameTitle = false;
                
                Map<String, Object> properties = tXml.getProperties();
                Iterator it = properties.keySet().iterator();
                while (it.hasNext()) {
                    String title = (String) it.next();
                    if (title.equals(tos.getTopicTitle())) sameTitle = true;
                }
                
                if (sameTitle) {
                    errors.rejectValue("topicTitle", "error.exist", null, "Value already created.");                  
                }
            }   
        }
    }
}