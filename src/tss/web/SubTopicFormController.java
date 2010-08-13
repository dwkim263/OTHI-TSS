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
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tss.service.SubTopicSave;
import tss.service.SubTopicManager;
import tss.service.Selectings;
import tss.service.CompletionMessage;

public class SubTopicFormController extends SimpleFormController {

    protected final Log logger = LogFactory.getLog(getClass());
    
    private SubTopicManager subTopicManager;
    
    private Selectings selectings;
    
    private CompletionMessage completionMessage;       
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request) throws Exception {    

        String courseId = selectings.getCourseId();
        String courseTitle = selectings.getCourseTitle();  
        String topicTitle = selectings.getTopicTitle();     
    
   
        Map<String, Object> tagMap = new HashMap<String, Object>();
        tagMap.put("courseId", courseId);
        tagMap.put("courseTitle", courseTitle);       
        tagMap.put("topicTitle", topicTitle);           
        return tagMap;
    }        
        
    @Override
    public ModelAndView onSubmit(Object command) throws ServletException {

        String courseId = ((SubTopicSave) command).getCourseId(); 
        String topicTitle = ((SubTopicSave) command).getTopicTitle();      
        String subTopicTitle = ((SubTopicSave) command).getSubTopicTitle();  
        String subTopicDescription = ((SubTopicSave) command).getSubTopicDescription().replaceAll("\r\n"," ");
        String subTopicClue = ((SubTopicSave) command).getSubTopicClue().replaceAll("\r\n"," ");
                     
        subTopicManager.createSubTopicXML
                (courseId, topicTitle, subTopicTitle, subTopicDescription, subTopicClue);
        
        completionMessage.setMessage("Adding a sub-topc");
        
        logger.info("returning from PriceIncreaseForm view to " + getSuccessView());

        return new ModelAndView(new RedirectView(getSuccessView()));
    }    
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        SubTopicSave subTopicSave = new SubTopicSave();
        subTopicSave.setCourseId(selectings.getCourseId());  
        subTopicSave.setTopicTitle(selectings.getTopicTitle());        
        subTopicSave.setSubTopicTitle("Sub Topic Title");        
        subTopicSave.setSubTopicDescription("Sub Topic Description");
        subTopicSave.setSubTopicClue("Sub Topic Clue");
        return subTopicSave;
    }

    public void setSubTopicManager(SubTopicManager subTopicManager) {
        this.subTopicManager = subTopicManager;
    }

    public SubTopicManager getSubTopicManager() {
        return subTopicManager;
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