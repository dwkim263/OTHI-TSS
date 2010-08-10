package tss.domain;

/**
 *
 * @author Dong Won Kim
 */
import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ToolSupport extends PropertyEditorSupport {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public String getAsText() {
      Object value = getValue();
      return value == null ? "" : ((Tool) value).getName();
    }

    @Override
    public void setAsText(String toolName) {
        Tool tool = Tool.getById(toolName);
        
        if (tool == null) {
          throw new IllegalArgumentException("Invalid id for GameMap: " + toolName);
        }
        setValue(tool);
    }    
}
