/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.service;

/**
 *
 * @author Steve
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.File;
import java.util.*;
import tss.domain.DefaultProperties;
import tss.domain.xml.EvaluationStationXML;
import tss.domain.xml.TreasureXML;
import tss.domain.xml.TopicEvaluationXML;
import tss.domain.xml.TopicOrientationXML;

public class EvaluationEnvironmentManager {

    protected final Log logger = LogFactory.getLog(getClass());

    private EvaluationStationXML evaluationStationXML;
    private TreasureXML treasureXML;
    private TopicEvaluationXML evaluationXML;
    private TopicOrientationXML orientationXML;

    public void createEvaluationStation(BuildingEnvironment buildingEnvironment) {
        String courseId = buildingEnvironment.getCourseId();
        String topic = buildingEnvironment.getTopicTitle();
        String stationName = buildingEnvironment.getStation();
        String placeName = buildingEnvironment.getPlaceName();
        String placeNickName = buildingEnvironment.getPlaceNickName();
        String treasureBoxName = buildingEnvironment.getTreasureBoxName();


        evaluationXML = new TopicEvaluationXML(DefaultProperties.getAppRoot() + File.separator + courseId + File.separator + DefaultProperties.EVALUATION_XML);
        Map<String, Object> properties = evaluationXML.getProperties();
        Map<String, String> property = (Map<String, String>) properties.get(topic);
        String clue = property.get("clue");

        evaluationStationXML = new EvaluationStationXML(courseId, treasureBoxName, clue);
        evaluationStationXML.putFirstNode(new String[]{topic, stationName, placeName, placeNickName});
        evaluationStationXML.putTextNodes();
        evaluationStationXML.writeXML();        
    }

    public void createTreasure (BuildingEnvironment buildingEnvironment)
    {
        String courseId = buildingEnvironment.getCourseId();
        String topic = buildingEnvironment.getTopicTitle();
        String placeName = buildingEnvironment.getPlaceName();
        String treasureBoxName = buildingEnvironment.getTreasureBoxName();
        String golds = buildingEnvironment.getGolds();
        String exps = buildingEnvironment.getExperiencePoints();
        String tool = buildingEnvironment.getTool();
        String armor = buildingEnvironment.getArmor();
        String weapon = buildingEnvironment.getWeapon();
        String conclusion = buildingEnvironment.getConclusion();

        evaluationXML = new TopicEvaluationXML(DefaultProperties.getAppRoot() + File.separator + courseId + File.separator + DefaultProperties.EVALUATION_XML);
        Map<String, Object> properties = evaluationXML.getProperties();
        Map<String, String> property = (Map<String, String>) properties.get(topic);
        String sampleAnswer = property.get("sample_answer");

        orientationXML = new TopicOrientationXML(DefaultProperties.getAppRoot() + File.separator + courseId + File.separator + DefaultProperties.ORIENTATION_XML);
        properties = orientationXML.getProperties();
        property = (Map<String, String>) properties.get(topic);
        String question = property.get("question");

        treasureXML = new TreasureXML(courseId, question, sampleAnswer, conclusion, golds, exps, tool, armor, weapon);

        treasureXML.putFirstNode(new String[]{topic, placeName, treasureBoxName});
        treasureXML.putTextNodes();
        treasureXML.writeXML();
    }

    public EvaluationStationXML getEvaluationStationXML() {
        return evaluationStationXML;
    }

    public void setEvaluationStationXML(EvaluationStationXML evaluationStationXML) {
        this.evaluationStationXML = evaluationStationXML;
    }

    public TreasureXML getTreasureXML() {
        return treasureXML;
    }

    public void setTreasureXML(TreasureXML treasureXML) {
        this.treasureXML = treasureXML;
    }    
}
