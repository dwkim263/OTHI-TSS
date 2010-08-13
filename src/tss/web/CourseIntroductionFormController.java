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

import tss.service.CourseIntroductionSave;
import tss.service.CourseIntroductionManager;
import tss.service.Selectings;
        
public class CourseIntroductionFormController extends SimpleFormController {

    protected final Log logger = LogFactory.getLog(getClass());
    
    private CourseIntroductionManager courseIntroductionManager;
    private Selectings selectings;
    
    @Override
    public ModelAndView onSubmit(Object command) throws ServletException {

        String courseId = ((CourseIntroductionSave) command).getCourseId();        
        String courseTitle = ((CourseIntroductionSave) command).getCourseTitle();
        String courseObjective = ((CourseIntroductionSave) command).getCourseObjective().replaceAll("\r\n"," ");
        String courseReferences = ((CourseIntroductionSave) command).getCourseReferences().replaceAll("\r\n"," ");
          
        //build introduction xml
        courseIntroductionManager.createCourseIntroductionXML
                (courseId, courseTitle, courseObjective, courseReferences);

        selectings.setCourseId(courseId);
        selectings.setCourseTitle(courseTitle);  
        
        logger.info("returning from CourseIntroductionForm view to " + getSuccessView());

        return new ModelAndView(new RedirectView(getSuccessView()));
    }    
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        CourseIntroductionSave courseIntroductionSave = new CourseIntroductionSave();
        courseIntroductionSave.setCourseId("MA"); //Mathematics
        courseIntroductionSave.setCourseTitle("Course Title");
        courseIntroductionSave.setCourseObjective("Course Objective");
        courseIntroductionSave.setCourseReferences("Course References");
        return courseIntroductionSave;
    }

    public void setCourseIntroductionManager(CourseIntroductionManager courseIntroductionManager) {
        this.courseIntroductionManager = courseIntroductionManager;
    }

    public CourseIntroductionManager getCourseIntroductionManager() {
        return courseIntroductionManager;
    }

    public Selectings getSelectings() {
        return selectings;
    }

    public void setSelectings(Selectings selectings) {
        this.selectings = selectings;
    }
    
}