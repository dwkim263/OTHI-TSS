package tss.domain;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GameMapSupport extends PropertyEditorSupport {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public String getAsText() {
      Object value = getValue();
      return value == null ? "" : ((GameMap)value).getPlaceName();
    }

    @Override
    public void setAsText(String placeName) {
        GameMap gameMap = GameMap.getById(placeName);
        
        if (gameMap == null) {
          throw new IllegalArgumentException("Invalid id for GameMap: " + placeName);
        }
        setValue(gameMap);
    }    
}
