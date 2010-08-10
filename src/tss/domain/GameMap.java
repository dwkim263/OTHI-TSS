package tss.domain;

/**
 *
 * @author Steve
 */
import tss.domain.xml.PlaceXML;
import tss.domain.xml.GuideXML;
import java.util.*;
import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GameMap {
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static Map<String, GameMap> gameMaps = new HashMap<String, GameMap>();

    private static List<String> placesForNPC = new LinkedList<String>();

    private static List<String> placesForTreasureBox = new LinkedList<String>();

    private static PlaceXML placeXML;

    private List<String> gameGuides = new LinkedList<String>();

    private List<String> treasureBoxes = new LinkedList<String>();
    
    private String placeName;
    
    private GameMap(String placeName){
        gameMaps.put(placeName, this);
    }

    public String getPlaceName() {
        return placeName;
    }
    
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public static List<String> getPlacesForNPC(String courseId, String topicTitle) {
        setGameMaps(courseId, topicTitle);
        if (!gameMaps.isEmpty()) {
            setGuides(courseId, topicTitle);
        }
        return placesForNPC;
    }

    public void setPlacesForNPC(List<String> placesForNPC) {
        GameMap.placesForNPC = placesForNPC;
    }

    public static List<String> getPlacesForTreasureBox(String courseId, String topicTitle) {
        setGameMaps(courseId, topicTitle);
        if (!gameMaps.isEmpty()) {
            setTreasureBoxes(courseId);
        }
        return placesForTreasureBox;
    }

    public void setPlacesForTreasureBox(List<String> placesForTreasureBox) {
        GameMap.placesForTreasureBox = placesForTreasureBox;
    }

    public static List<String> getGameGuides(String courseId, String topicTitle, String placeName) {
        GameMap gameMap = GameMap.getGameMaps(courseId, topicTitle).get(placeName);
        if (gameMap != null) {
            List<String> guideNames = gameMap.getGameGuides();

            if (guideNames != null) {
                return guideNames;
            } else {
                return null;                
            }
        } else {
            return null;
        }
    }
        
    public List<String> getGameGuides() {
        return gameGuides;
    }
    
    public void setGameGuides(List<String> gameGuides) {
        this.gameGuides = gameGuides;
    }

    public static List<String> getTreasureBoxes(String courseId, String topicTitle, String placeName) {
        GameMap gameMap = GameMap.getGameMaps(courseId, topicTitle).get(placeName);
        if (gameMap != null) {
            List<String> treasureBoxNames = gameMap.getTreasureBoxes();
            if (treasureBoxNames != null) {
                return treasureBoxNames;
            } else {
                return null;              
            }
        } else {
            return null;
        }
    }

    public List<String> getTreasureBoxes() {
        return treasureBoxes;
    }

    public void setTreasureBoxes(List<String> treasureBoxes) {
        this.treasureBoxes = treasureBoxes;
    }
    
    public static void setGameMaps(Map<String, GameMap> gameMaps) {
        GameMap.gameMaps = gameMaps;
    }    
   
    public static GameMap getById(String placeName) {
        return gameMaps.get(placeName);
    }


    public static Map<String, GameMap> getGameMaps(String courseId, String topicTitle) {
        setGameMaps(courseId, topicTitle);
        return gameMaps;
    }    

    public static void setGuides(String courseId, String topicTitle) {
        //npc load
        Map <String, Object> npcs = placeXML.getObjectProperties("npc");

        List<String> places = new LinkedList<String>();

        Iterator it = npcs.keySet().iterator();
        while(it.hasNext()) {
            String placeName = (String) it.next();

            List<String> npcNames = (List<String>) npcs.get(placeName);

            if (npcNames !=null && npcNames.size()>0) {
                //check available guides by referring to guide xml
                List<String> guides = new LinkedList<String>();

                GuideXML guideXML = new GuideXML(DefaultProperties.getAppRoot() + File.separator + courseId + File.separator + DefaultProperties.GUIDE_XML);
                Map<String, Object> guideMap = guideXML.getProperties();

                for (String guide: npcNames) {
                   if (!guideMap.containsKey(topicTitle + ":" + placeName + ":" + guide)) guides.add(guide);
                }

                if (!guides.isEmpty()) {
                    GameMap gameMap = new GameMap(placeName);
                    places.add(placeName);
                    gameMap.setGameGuides(guides);
                }
            }
        }
        GameMap.placesForNPC = places;
    }

    public static void setTreasureBoxes(String courseId) {
                //treasureBox load
        Map <String, Object> treasureBoxes = placeXML.getObjectProperties("treasure");

        List<String> places = new LinkedList<String>();

        Iterator it = treasureBoxes.keySet().iterator();
        while(it.hasNext()) {
            String placeName = (String) it.next();

            List<String> treasureBoxNames = (List<String>) treasureBoxes.get(placeName);
            if (treasureBoxNames !=null && treasureBoxNames.size()>0) {
                GameMap gameMap = getById(placeName);
                if (gameMap == null) gameMap = new GameMap(placeName);
                places.add(placeName);
                gameMap.setTreasureBoxes(treasureBoxNames);
            }
        }
        GameMap.placesForTreasureBox = places;
    }

    public static void setGameMaps(String courseId, String topicTitle) {
        PlaceXML placeXml = new PlaceXML();
        setPlaceXML(placeXml);
        setGuides(courseId, topicTitle);
        setTreasureBoxes(courseId);
    }

    public static PlaceXML getPlaceXML() {
        return placeXML;
    }

    public static void setPlaceXML(PlaceXML placeXml) {
        GameMap.placeXML = placeXml;
    }
}
