package tss.domain.xml;

/**
 *
 * @author Dong Won Kim
 */
import java.util.List;

public class OrientationStationXML extends StationXML {
       
    private final static String[] textElementNames = new String[]{"guide", "next_quests"};
    private String[] textNodeValues = new String[2];
        
    public OrientationStationXML(String courseId, String guideName, List<String> nextStations) {
        super(courseId, textElementNames);
        setTextNodeValues(guideName, nextStations);

    }      

    public void putTextNodes(){
        putTextNodes(textNodeValues);
    }
    
    public void setTextNodeValues (String guideName, List<String> nextStations ){
        textNodeValues[0] = guideName;
        String quests = "";
        for (String quest: nextStations) {
            if (quests.isEmpty()) quests = quest;
            else quests = quests + "," + quest;
        }
        textNodeValues[1] = quests;
    }        
}
