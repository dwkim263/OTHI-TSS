package tss.domain;

/**
 *
 * @author Dong Won Kim
 */
import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WeaponSupport extends PropertyEditorSupport {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public String getAsText() {
      Object value = getValue();
      return value == null ? "" : ((Weapon) value).getName();
    }

    @Override
    public void setAsText(String weaponName) {
        Weapon weapon = Weapon.getById(weaponName);
        
        if (weapon == null) {
          throw new IllegalArgumentException("Invalid id for GameMap: " + weaponName);
        }
        setValue(weapon);
    }    
}
