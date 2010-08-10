package tss.web;

/**
 *
 * @author Dong Won Kim
 */
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tss.service.TopicEvaluationSave;
import tss.service.TopicEvaluationManager;
import tss.service.Selectings;
import tss.service.CompletionMessage;
        
public class TopicEvaluationFormController extends SimpleFormController {

    protected final Log logger = LogFactory.getLog(getClass());
    
    private TopicEvaluationManager topicEvaluationManager;

    private Selectings selectings;
    
    private CompletionMessage completionMessage;        
        
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request) throws Exception {    
        String courseId = selectings.getCourseId();
        String courseTitle = selectings.getCourseTitle();  
        String topicTitle = selectings.getTopicTitle();
        String topicQuestion = selectings.getTopicQuestion();
       
        Map<String, Object> tagMap = new HashMap<String, Object>();
        tagMap.put("courseId", courseId);
        tagMap.put("courseTitle", courseTitle);        
        tagMap.put("topicTitle", topicTitle);
        tagMap.put("topicQuestion", topicQuestion);           
        return tagMap;
    }    
    
    @Override
    public ModelAndView onSubmit(Object command) throws ServletException {
        String courseId = ((TopicEvaluationSave) command).getCourseId();             
        String topicTitle = ((TopicEvaluationSave) command).getTopicTitle();         
        String topicSampleAnswer = ((TopicEvaluationSave) command).getTopicSampleAnswer().replaceAll("\r\n"," ");
        String topicConclusion = ((TopicEvaluationSave) command).getTopicConclusion().replaceAll("\r\n"," ");
        String treasureClue = ((TopicEvaluationSave) command).getTreasureClue().replaceAll("\r\n"," ");
        
        topicEvaluationManager.createTopicEvaluationXML
                (courseId, topicTitle, topicSampleAnswer, topicConclusion, treasureClue);
        
        completionMessage.setMessage("Creating a topic evaluation");
                
        logger.info("returning from PriceIncreaseForm view to " + getSuccessView());

        return new ModelAndView(new RedirectView(getSuccessView()));
    }    
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        TopicEvaluationSave topicEvaluationSave = new TopicEvaluationSave();
        topicEvaluationSave.setCourseId(selectings.getCourseId());
        topicEvaluationSave.setTopicTitle(selectings.getTopicTitle());
        topicEvaluationSave.setTopicSampleAnswer("Sample answer");
        topicEvaluationSave.setTopicConclusion("Topic conclusion");
        return topicEvaluationSave;
    }

    public void setTopicEvaluationManager(TopicEvaluationManager topicEvaluationManager) {
        this.topicEvaluationManager = topicEvaluationManager;
    }

    public TopicEvaluationManager getTopicEvaluationManager() {
        return topicEvaluationManager;
    }

    public Selectings getSelectings() {
        return selectings;
    }

    public void setSelectings(Selectings selectings) {
        this.selectings = selectings;
    }

    public CompletionMessage getCompletionMessage() {
        return completionMessage;
    }

    public void setCompletionMessage(CompletionMessage completionMessage) {
        this.completionMessage = completionMessage;
    }        
}