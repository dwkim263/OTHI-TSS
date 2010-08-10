/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.service;

/**
 *
 * @author Steve
 */
import java.util.*;
import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.DefaultProperties;
import tss.domain.xml.SubtopicStationXML;
import tss.domain.xml.GuideXML;
import tss.domain.xml.SubTopicXML;

public class SubtopicEnvironmentManager {

    protected final Log logger = LogFactory.getLog(getClass());

    private SubtopicStationXML subtopicStationXML;
    private GuideXML guideXML;
    private SubTopicXML subTopicXML;

    public void createSubtopicStation(BuildingEnvironment buildingEnvironment)
    {
        String courseId = buildingEnvironment.getCourseId();
        String topic = buildingEnvironment.getTopicTitle();
        String stationName = buildingEnvironment.getStation(); //The name of sub-topic is the same as the name of the stationName.
        String placeName = buildingEnvironment.getPlaceName();
        String placeNickName = buildingEnvironment.getPlaceNickName();

        String guideName = buildingEnvironment.getGuideName();
        List<String> nextStations = buildingEnvironment.getNextStations();
  
        subTopicXML = new SubTopicXML(DefaultProperties.getAppRoot() + File.separator + courseId + File.separator + DefaultProperties.SUB_TOPIC_XML);
        Map<String, Object> properties = subTopicXML.getProperties();
        Map<String, String> property = (Map<String, String>) properties.get(topic + ":" + stationName);
        String clue = property.get("clue");

        //create SubtopicStation XML
        subtopicStationXML = new SubtopicStationXML(courseId, guideName, nextStations, clue);
        subtopicStationXML.putFirstNode(new String[]{topic, stationName, placeName, placeNickName});
        subtopicStationXML.putTextNodes();
        subtopicStationXML.writeXML();

        String guideNickName = buildingEnvironment.getGuideNickName();
        String talk = buildingEnvironment.getDescription();
        String question = "";
        String clueForGuide = "";

        //create guide XML
        guideXML = new GuideXML(courseId, talk, question, clueForGuide);
        guideXML.putFirstNode(new String[]{topic, placeName, guideName, guideNickName});
        guideXML.putTextNodes();
        guideXML.writeXML();
    }
}
