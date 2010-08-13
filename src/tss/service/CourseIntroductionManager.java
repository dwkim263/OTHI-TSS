package tss.service;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.xml.CourseIntroductionXML;

public class CourseIntroductionManager implements Serializable{

    protected final Log logger = LogFactory.getLog(getClass());
    
    private CourseIntroductionXML courseIntroductionXML;
    
    public void createCourseIntroductionXML
            (String courseId, String courseTitle, String courseObjective, String courseReferences) {
                
        courseIntroductionXML = new CourseIntroductionXML
                (courseId, courseTitle, courseObjective, courseReferences);
        courseIntroductionXML.putTextNodes();
        courseIntroductionXML.writeXML();
    }
    
    public CourseIntroductionXML getCourseIntroduction() {
        return courseIntroductionXML;
    }
}
