package tss.domain;

/**
 *
 * @author Dong Won Kim
 */
import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SubTopicSupport extends PropertyEditorSupport {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public String getAsText() {
      Object value = getValue();
      return value == null ? "" :
          ((SubTopic)value).getCourseId() + ":" + ((SubTopic)value).getTopicTitle() + ":" + ((SubTopic)value).getSubTopicTitle();
    }

    @Override
    public void setAsText(String id) {
        SubTopic subTopic = SubTopic.getById(id);
        
        if (subTopic == null) {
          throw new IllegalArgumentException("Invalid id for SubTopic: " + id);
        }
        setValue(subTopic);
    }    
}
