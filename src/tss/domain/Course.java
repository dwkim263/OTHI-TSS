/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.domain;

/**
 *
 * @author Steve
 */
import tss.domain.xml.CourseIntroductionXML;
import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class Course {
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static Map<String, Course> courses = new HashMap<String, Course>();

    private String courseId;
    private String courseTitle;
        
    private Course(String courseId, String courseTitle){
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        courses.put(courseId, this);
    }
      
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public static Map<String, Course> getCourses() {
        if (courses.isEmpty()) {
            setCourses();            
        }
        return courses;
    }
    
    public static void setCourses() {
        
        File[] directories = (new File(DefaultProperties.getAppRoot())).listFiles();
        
        for (File directory: directories) {
            if (directory.isDirectory()) {                
                CourseIntroductionXML introductionXml = null;
                File[] files = directory.listFiles();
                for (File file: files) {
                    if (file.getName().equals(DefaultProperties.INTRODUCTION_XML)) {
                        introductionXml = new CourseIntroductionXML(file.getAbsolutePath());
                    }
                }
                
                if (introductionXml != null) {
                    Map <String, Object> introduction = introductionXml.getProperties();             
                    new Course((String) introduction.get("id"), (String) introduction.get("title"));
                }
            }
        }    
    }
        
    public static void setCourses(Map<String, Course> courses) {
        Course.courses = courses;
    }
    
   
    public static Course getById(String id) {
        return courses.get(id);
    }    
}
