/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.service;

/**
 *
 * @author Steve
 */
public class CourseIntroductionSave {

    private String courseId;    
    private String courseTitle;
    private String courseObjective;    
    private String courseReferences;
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseObjective(String objective) {
        courseObjective = objective;
    }

    public void setCourseReferences(String references) {
        courseReferences = references;
    }

    public void setCourseTitle(String title) {
        courseTitle = title;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseObjective() {
        return courseObjective;
    }

    public String getCourseReferences() {
        return courseReferences;
    }

    public String getCourseTitle() {
        return courseTitle;
    }
}
