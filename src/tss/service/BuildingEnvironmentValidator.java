/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.service;

/**
 *
 * @author Steve
 */
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BuildingEnvironmentValidator implements Validator {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return BuildingEnvironment.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        BuildingEnvironment bes = (BuildingEnvironment) obj;
        
        if (bes.getDescription() == null) {
            errors.rejectValue("stationDescription", "error.not-specified", null, "Value required.");          
        } 
    }

}