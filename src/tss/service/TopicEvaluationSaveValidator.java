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
import tss.domain.xml.TopicEvaluationXML;
import tss.domain.DefaultProperties;

public class TopicEvaluationSaveValidator implements Validator {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return TopicEvaluationSave.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        TopicEvaluationSave tes = (TopicEvaluationSave) obj;
        if (tes.getTopicSampleAnswer() == null) {
            errors.rejectValue("topicSampleAnswer", "error.not-specified", null, "Value required.");
        } else if (tes.getTopicConclusion() == null) {
            errors.rejectValue("topicConclusion", "error.not-specified", null, "Value required.");      
        } else if (tes.getTreasureClue() == null) {
            errors.rejectValue("treasure clue", "error.not-specified", null, "Value required.");
        } else {
            TopicEvaluationXML tXml = new TopicEvaluationXML (        
                    DefaultProperties.getAppRoot() + File.separator + tes.getCourseId() + File.separator + DefaultProperties.EVALUATION_XML);
            
            if (tXml.getXmlFile().isExisted()) {
                
                boolean exist = false;
                
                Map<String, Object> properties = tXml.getProperties();
                Iterator it = properties.keySet().iterator();
                while (it.hasNext()) {
                    String title = (String) it.next();
                    if (title.equals(tes.getTopicTitle())) exist = true;
                }
                
                if (exist) {
                    errors.rejectValue("topicSampleAnswer", "error.exist", null, "Value already created.");                  
                }
            }
        }
        
    }
}