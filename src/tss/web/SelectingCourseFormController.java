/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.web;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.HashMap;
import tss.domain.Course;
import tss.service.SelectingCourse;
import tss.service.Selectings;
        
public class SelectingCourseFormController extends SimpleFormController {

    protected final Log logger = LogFactory.getLog(getClass());   
    
    private Selectings selectings;
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
        Map<String, Object> tagMap = new HashMap<String, Object>();
        tagMap.put("courses", Course.getCourses());
        return tagMap;
    }

    @Override
    public ModelAndView onSubmit(Object command) throws ServletException {

        String course = ((SelectingCourse) command).getCourse();       
  
        int indexOfColon = course.indexOf(":");
        String courseId = course.substring(0, indexOfColon);
        String courseTitle = course.substring(indexOfColon + 1);   
       
        selectings.setCourseId(courseId);
        selectings.setCourseTitle(courseTitle);
  
        logger.info("returning from SelectingCourseForm view to " + getSuccessView());
        
//        return new ModelAndView(new RedirectView(getSuccessView()), model);
        return new ModelAndView(new RedirectView(getSuccessView()));     
    }    
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        SelectingCourse selectingCourse = new SelectingCourse();
        return selectingCourse;
    }

    public Selectings getSelectings() {
        return selectings;
    }

    public void setSelectings(Selectings selectings) {
        this.selectings = selectings;
    }
        
}