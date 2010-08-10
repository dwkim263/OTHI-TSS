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
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.xml.CourseIntroductionXML;
import tss.domain.DefaultProperties;

public class CourseIntroductionSaveValidator implements Validator {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return CourseIntroductionSave.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        CourseIntroductionSave cis = (CourseIntroductionSave) obj;
        
        if (cis.getCourseId() == null) {
            errors.rejectValue("courseId", "error.not-specified", null, "Value required.");            
        } else if (cis.getCourseTitle() == null) {
            errors.rejectValue("courseTitle", "error.not-specified", null, "Value required.");            
        } else if (cis.getCourseObjective() == null) {
            errors.rejectValue("courseObjective", "error.not-specified", null, "Value required.");            
        } else if (cis.getCourseReferences() == null) {
            errors.rejectValue("courseReferences", "error.not-specified", null, "Value required.");            
        } else {
            CourseIntroductionXML cXml = new CourseIntroductionXML(
                    DefaultProperties.getAppRoot() + File.separator + cis.getCourseId() + File.separator + DefaultProperties.INTRODUCTION_XML);
            if (cXml.getXmlFile().isExisted()) {
                errors.rejectValue("courseId", "error.exist", null, "Cannot create.");    
            }
        }
    }

}