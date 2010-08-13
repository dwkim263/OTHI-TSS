package tss.domain.xml;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SubtopicStationXML extends StationXML {
    
    protected final Log logger = LogFactory.getLog(getClass());       
    
    private final static String[] textElementNames = new String[]{"guide", "next_quests", "clue"};

    private String[] textNodeValues = new String[3];
            
        
    public SubtopicStationXML(String courseId, String guideName, List<String> nextStations, String clue) {
        super(courseId, textElementNames);
        setTextNodeValues(guideName, nextStations, clue);
    }

    public void putTextNodes(){
        putTextNodes(textNodeValues);
    }
            
    public void setTextNodeValues (String guideName, List<String> nextStations, String clue ){
        textNodeValues[0] = guideName;
        String quests = "";
        for (String quest: nextStations) {
            if (quests.isEmpty()) quests = quest;
            else quests = quests + "," + quest;
        }
        textNodeValues[1] = quests;
        textNodeValues[2] = clue;
    }
}
