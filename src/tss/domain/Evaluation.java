/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.domain;

/**
 *
 * @author Steve
 */
import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.xml.CourseIntroductionXML;
import tss.domain.xml.TopicEvaluationXML;

public class Evaluation {
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static Map<String, Evaluation> evaluations = new HashMap<String, Evaluation>();

    private String courseId;
    private String topicTitle;     
    private String topicSampleAnswer;      
    private String topicConclusion;
    private String treasureClue;

    private Evaluation(String courseId, String topicTitle,
            String topicSampleAnswer, String topicConclusion, String clue){
        this.courseId = courseId;
        this.topicTitle = topicTitle;        
        this.topicSampleAnswer = topicSampleAnswer;   
        this.topicConclusion = topicConclusion;
        this.treasureClue = clue;
        evaluations.put(courseId + ":" + topicTitle, this);        
    }
      
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public static Map<String, Evaluation> getEvaluations(String courseId) {
        setEvaluations(courseId);        
        return evaluations;
    }
    
    public static void setEvaluations(String courseId) {
        CourseIntroductionXML introductionXml = null;
        TopicEvaluationXML topicEvaluationXml = null;

        File[] files = (new File(DefaultProperties.getAppRoot() + File.separator + File.separator + courseId)).listFiles();
        for (File file: files) {
            if (file.getName().equals(DefaultProperties.INTRODUCTION_XML)) {
                introductionXml = new CourseIntroductionXML(file.getAbsolutePath());
            } else if (file.getName().equals(DefaultProperties.EVALUATION_XML)) {
                topicEvaluationXml = new TopicEvaluationXML(file.getAbsolutePath());
            }
        }

        if (introductionXml != null && topicEvaluationXml != null) {
            Map <String, Object> introduction = introductionXml.getProperties();
            Map <String, Object> evaluation = topicEvaluationXml.getProperties();
            Iterator it = evaluation.keySet().iterator();
            while(it.hasNext()) {
                String title = (String) it.next();
                Map <String, String> topic = (Map <String, String>) evaluation.get(title);
                new Evaluation((String) introduction.get("id"), title, topic.get("sample_answer"),
                        topic.get("conclusion"), topic.get("clue") );
            }
        }
    }
        
    public static void setEvaluations(Map<String, Evaluation> evaluations) {
        Evaluation.evaluations = evaluations;
    }
    
   
    public static Evaluation getById(String id) {
        return evaluations.get(id);
    }

    public String getTopicConclusion() {
        return topicConclusion;
    }

    public void setTopicConclusion(String topicConclusion) {
        this.topicConclusion = topicConclusion;
    }

    public String getTopicSampleAnswer() {
        return topicSampleAnswer;
    }

    public void setTopicSampleAnswer(String topicSampleAnswer) {
        this.topicSampleAnswer = topicSampleAnswer;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTreasureClue() {
        return treasureClue;
    }

    public void setTreasureClue(String treasureClue) {
        this.treasureClue = treasureClue;
    }
    
}
