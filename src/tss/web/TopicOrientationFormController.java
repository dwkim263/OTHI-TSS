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
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tss.service.TopicOrientationSave;
import tss.service.TopicOrientationManager;
import tss.service.Selectings;

public class TopicOrientationFormController extends SimpleFormController {

    protected final Log logger = LogFactory.getLog(getClass());
    
    private TopicOrientationManager topicOrientationManager;
    
    private Selectings selectings;
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request) throws Exception {    

        String courseId = selectings.getCourseId();
        String courseTitle = selectings.getCourseTitle();  
    
   
        Map<String, Object> tagMap = new HashMap<String, Object>();
        tagMap.put("courseId", courseId);
        tagMap.put("courseTitle", courseTitle);        
        return tagMap;
    }    
    
    @Override
    public ModelAndView onSubmit(Object command) throws ServletException {
        String courseId = ((TopicOrientationSave) command).getCourseId();             
        String topicTitle = ((TopicOrientationSave) command).getTopicTitle();    
        String topicIntroduction = ((TopicOrientationSave) command).getTopicIntroduction().replaceAll("\r\n"," ");
        String topicQuestion = ((TopicOrientationSave) command).getTopicQuestion().replaceAll("\r\n"," ");
        
        topicOrientationManager.createTopicOrientationXML
                (courseId, topicTitle, topicIntroduction, topicQuestion);
        selectings.setTopicTitle(topicTitle);
        selectings.setTopicQuestion(topicQuestion);
        
        logger.info("returning from TopicOrientationForm view to " + getSuccessView());

        return new ModelAndView(new RedirectView(getSuccessView()));
    }    
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        TopicOrientationSave topicOrientationSave = new TopicOrientationSave();
        topicOrientationSave.setCourseId(selectings.getCourseId());
        topicOrientationSave.setTopicTitle("Topic Title");    
        topicOrientationSave.setTopicIntroduction("Topic Introduction");
        topicOrientationSave.setTopicQuestion("Topic Question");
        return topicOrientationSave;
    }

    public void setTopicOrientationManager(TopicOrientationManager topicOrientationManager) {
        this.topicOrientationManager = topicOrientationManager;
    }

    public TopicOrientationManager getTopicOrientationManager() {
        return topicOrientationManager;
    }

    public void setSelectings(Selectings selectings) {
        this.selectings = selectings;
    }

    public Selectings getSelectings() {
        return selectings;
    }    
}