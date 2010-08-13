package tss.web;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.HashMap;

import tss.domain.Course;
import tss.domain.Topic;
import tss.domain.SubTopic;
import tss.domain.GameMap;
import tss.domain.Evaluation;
import tss.domain.Tool;
import tss.domain.Armor;
import tss.domain.Weapon;
import tss.domain.PlaceNickName;
import tss.service.OrientationEnvironmentManager;
import tss.service.SubtopicEnvironmentManager;
import tss.service.EvaluationEnvironmentManager;
import tss.service.BuildingEnvironment;
import tss.service.CompletionMessage;
        
public class BuildingEnvironmentFormController extends SimpleFormController {

    protected final Log logger = LogFactory.getLog(getClass());                   

    private EvaluationEnvironmentManager evaluationEnvironmentManager;
    
    private OrientationEnvironmentManager orientationEnvironmentManager;

    private SubtopicEnvironmentManager subtopicEnvironmentManager;

    private CompletionMessage completionMessage;
/*
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
 
        binder.registerCustomEditor(Course.class, new CourseSupport());
        binder.registerCustomEditor(Topic.class, new TopicSupport());           
        binder.registerCustomEditor(SubTopic.class, new SubTopicSupport());    
        binder.registerCustomEditor(Evaluation.class, new EvaluationSupport());
        binder.registerCustomEditor(GameMap.class, new GameMapSupport());
        binder.registerCustomEditor(Tool.class, new ToolSupport());
        binder.registerCustomEditor(Armor.class, new ArmorSupport());
        binder.registerCustomEditor(Weapon.class, new WeaponSupport());
    }
 */

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
        Map<String, Object> tagMap = new HashMap<String, Object>();
        tagMap.put("courses", Course.getCourses());

        String courseId = request.getParameter("courseId");
        String topicTitle = null;
        if (courseId != null) {
            tagMap.put("topics", Topic.getTopics(courseId));
            topicTitle = request.getParameter("topicTitle");
            if (topicTitle != null){
                tagMap.put("subTopics", SubTopic.getSubTopics(courseId));
            }
        }

        String stationName = request.getParameter("station");
        if (stationName != null) {
            String placeName = request.getParameter("placeName");

            if (placeName == null) {
                if (stationName.equals("Evaluation")) {
                    tagMap.put("places", GameMap.getPlacesForTreasureBox(courseId, topicTitle));
                } else {
                    tagMap.put("places", GameMap.getPlacesForNPC(courseId, topicTitle));
                }
            } else {
                String placeNickName = PlaceNickName.getPlaceName(topicTitle, placeName);
                if (placeNickName == null) {
                    placeNickName = request.getParameter("placeNickName");
                }
                tagMap.put("placeNickName", placeNickName);

                if (stationName.equals("Evaluation")) {

                    tagMap.put("evaluations", Evaluation.getEvaluations(courseId));

                    String treasureBoxName = request.getParameter("treasureBoxName");

                    if (treasureBoxName == null) {
                        tagMap.put("treasureBoxes", GameMap.getTreasureBoxes(courseId, topicTitle, placeName));
                    } else if (treasureBoxName != null) {
                        Evaluation evaluation = Evaluation.getById(courseId + ":" + topicTitle);
                        String topicConclusion = null;
                        if (evaluation != null) {
                            topicConclusion = evaluation.getTopicConclusion();
                        } else {
                            topicConclusion = "";
                        }
                        tagMap.put("conclusion", topicConclusion);
                        tagMap.put("tools", Tool.getTools());
                        tagMap.put("armors", Armor.getArmors());
                        tagMap.put("weapons", Weapon.getWeapons());
                    }
                } else {

                    String guideName = request.getParameter("guideName");

                    if (guideName == null) {
                        tagMap.put("gameGuides", GameMap.getGameGuides(courseId, topicTitle, placeName));
                    } else {
                        String[] nextStations = request.getParameterValues("nextStations");

                        if (nextStations != null) {
                            if (courseId != null && topicTitle != null && stationName.equals("Orientation")) {
                                String topicIntroduction = Topic.getById(courseId + ":" + topicTitle).getTopicIntroduction();
                                String topicQuestion = Topic.getById(courseId + ":" + topicTitle).getTopicQuestion();
                                tagMap.put("introduction", topicIntroduction);
                                tagMap.put("question", topicQuestion);
                            }
                        }
                    }
                }
            }
        }
        
        return tagMap;
    }   

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
                        Object command, BindException errors) throws Exception {

        String completedStationType = request.getParameter("completedStationType");

        if (completedStationType == null) {
            logger.info("returning from BuildingEnvironmentSaveForm view to BuildingEnvironmentSaveForm view");            
            return showForm(request, response, errors);

        } else {       

            if (completedStationType.equals("Evaluation")) {
                evaluationEnvironmentManager.createEvaluationStation((BuildingEnvironment)command);
                evaluationEnvironmentManager.createTreasure((BuildingEnvironment)command);

            } else if (completedStationType.equals("Orientation")) {
                orientationEnvironmentManager.createOrientatioStation((BuildingEnvironment)command);

            } else {
                subtopicEnvironmentManager.createSubtopicStation((BuildingEnvironment)command);
            }

            completionMessage.setMessage("Building a learning environment");

            logger.info("returning from BuildingEnvironmentSaveForm view to Next View" + getSuccessView());

            return new ModelAndView(new RedirectView(getSuccessView()));
        }
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        BuildingEnvironment buildingEnvironment = new BuildingEnvironment();
        buildingEnvironment.setDescription("Station description");   
        return buildingEnvironment;
    }

    public void setCompletionMessage(CompletionMessage completionMessage) {
        this.completionMessage = completionMessage;
    }

    public CompletionMessage getCompletionMessage() {
        return completionMessage;
    }

    public EvaluationEnvironmentManager getEvaluationEnvironmentManager() {
        return evaluationEnvironmentManager;
    }

    public void setEvaluationEnvironmentManager(EvaluationEnvironmentManager evaluationEnvironmentManager) {
        this.evaluationEnvironmentManager = evaluationEnvironmentManager;
    }

    public OrientationEnvironmentManager getOrientationEnvironmentManager() {
        return orientationEnvironmentManager;
    }

    public void setOrientationEnvironmentManager(OrientationEnvironmentManager orientationEnvironmentManager) {
        this.orientationEnvironmentManager = orientationEnvironmentManager;
    }

    public SubtopicEnvironmentManager getSubtopicEnvironmentManager() {
        return subtopicEnvironmentManager;
    }

    public void setSubtopicEnvironmentManager(SubtopicEnvironmentManager subtopicEnvironmentManager) {
        this.subtopicEnvironmentManager = subtopicEnvironmentManager;
    }
}