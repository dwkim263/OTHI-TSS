package tss.web;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.HashMap;

import tss.domain.Course;
import tss.domain.CourseSupport;
import tss.domain.Topic;
import tss.domain.TopicSupport;
import tss.service.SelectingTopic;
import tss.service.Selectings;
        
public class SelectingTopicFormController extends SimpleFormController {

    protected final Log logger = LogFactory.getLog(getClass());
                
    private Selectings selectings;
    
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Course.class, new CourseSupport());
        binder.registerCustomEditor(Topic.class, new TopicSupport());                
    }
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
        Map<String, Object> tagMap = new HashMap<String, Object>();
        tagMap.put("courses", Course.getCourses());
        String courseId = request.getParameter("courseId");
        if (courseId != null) {
            tagMap.put("topics", Topic.getTopics(courseId));    
        }
        return tagMap;
    }

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, 
                        Object command, BindException errors) throws Exception {  
        Topic topic = ((SelectingTopic) command).getTopic();
        
        if (topic == null) {
            logger.info("returning from SelectingTopicForm view to SelectingTopicForm view");            
            return showForm(request, response, errors);
        } else {
            
            selectings.setCourseId(topic.getCourseId());
            selectings.setCourseTitle(topic.getCourseTitle());
            selectings.setTopicTitle(topic.getTopicTitle()); 
            selectings.setTopicQuestion(topic.getTopicQuestion());     
            
            String successView = null;
            String doc = null;
            doc = request.getParameter("doc");          
            if (doc != null) { 
                if (doc.equals("eval")) {
                    successView = "topicEvaluation.html";
                } else if (doc.equals("sub")) {
                    successView = "subTopic.html";
                }

                setSuccessView(successView);

                logger.info("returning from SelectingTopicForm view to Next View" + getSuccessView()); 

                return new ModelAndView(new RedirectView(getSuccessView())); 
            } else {
                return showForm(request, response, errors);                
            }
        }
    }
    
    public Selectings getSelectings() {
        return selectings;
    }

    public void setSelectings(Selectings selectings) {
        this.selectings = selectings;
    }        
}