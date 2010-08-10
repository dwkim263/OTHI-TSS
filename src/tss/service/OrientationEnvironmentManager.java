/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.service;

/**
 *
 * @author Steve
 */
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tss.domain.xml.OrientationStationXML;
import tss.domain.xml.GuideXML;

public class OrientationEnvironmentManager {

    protected final Log logger = LogFactory.getLog(getClass());

    private OrientationStationXML orientaionStationXML;
    private GuideXML guideXML;

    public void createOrientatioStation(BuildingEnvironment buildingEnvironment) {

        String courseId = buildingEnvironment.getCourseId();
        String topic = buildingEnvironment.getTopicTitle();
        String stationName = buildingEnvironment.getStation();
        String placeName = buildingEnvironment.getPlaceName();
        String placeNickName = buildingEnvironment.getPlaceNickName();
        String guideName = buildingEnvironment.getGuideName();
        List<String> nextStations =  buildingEnvironment.getNextStations();

        orientaionStationXML = new OrientationStationXML(courseId, guideName, nextStations);

        orientaionStationXML.putFirstNode(new String[]{topic, stationName, placeName, placeNickName});
        orientaionStationXML.putTextNodes();
        orientaionStationXML.writeXML();
        
        String guideNickName = buildingEnvironment.getGuideNickName();
        String talk = buildingEnvironment.getIntroduction();
        String question = buildingEnvironment.getQuestion();
        String clue = "";

        //create guide XML
        guideXML = new GuideXML(courseId, talk, question, clue);
        guideXML.putFirstNode(new String[]{topic, placeName, guideName, guideNickName });
        guideXML.putTextNodes();
        guideXML.writeXML();
    }
}
