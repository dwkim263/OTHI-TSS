package tss.domain.xml;

/**
 *
 * @author Dong Won Kim
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EvaluationStationXML extends StationXML {
    
    protected final Log logger = LogFactory.getLog(getClass());       
    
    private final static String[] textElementNames = new String[]{"treasure_box", "clue"};

    private String[] textNodeValues = new String[2];
            
        
    public EvaluationStationXML(String courseId, String treasureBoxName, String clue) {
        super(courseId, textElementNames);
        setTextNodeValues(treasureBoxName, clue);
    }
    
    public void putTextNodes(){
        putTextNodes(textNodeValues);
    }
            
    public void setTextNodeValues (String treasureBoxName, String clue){
        textNodeValues[0] = treasureBoxName;
        textNodeValues[1] = clue;
    }
}
