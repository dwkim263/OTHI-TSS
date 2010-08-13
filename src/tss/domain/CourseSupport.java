/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.domain;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CourseSupport extends PropertyEditorSupport {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public String getAsText() {
      Object value = getValue();
      return value == null ? "" : ((Course)value).getCourseId();
    }

    @Override
    public void setAsText(String courseId) {
        Course course = Course.getById(courseId);
        
        if (course == null) {
          throw new IllegalArgumentException("Invalid id for Course: " + courseId);
        }
        setValue(course);
    }    
}
