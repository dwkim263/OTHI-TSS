package tss.domain;

/**
 *
 * @author Dong Won Kim
 */
import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ArmorSupport extends PropertyEditorSupport {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public String getAsText() {
      Object value = getValue();
      return value == null ? "" : ((Armor) value).getName();
    }

    @Override
    public void setAsText(String armorName) {
        Armor armor = Armor.getById(armorName);
        
        if (armor == null) {
          throw new IllegalArgumentException("Invalid id for GameMap: " + armorName);
        }
        setValue(armor);
    }    
}
