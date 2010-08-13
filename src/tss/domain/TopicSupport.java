package tss.domain;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TopicSupport extends PropertyEditorSupport {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public String getAsText() {
      Object value = getValue();
      return value == null ? "" : ((Topic)value).getCourseId() + ":" + ((Topic)value).getTopicTitle();
    }

    @Override
    public void setAsText(String id) {
        Topic topic = Topic.getById(id);
        
        if (topic == null) {
          throw new IllegalArgumentException("Invalid id for Topic: " + id);
        }
        setValue(topic);
    }    
}
