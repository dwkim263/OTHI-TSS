package tss.domain;

/**
 *
 * @author Dong Won (Steven) Kim
 */
import java.beans.PropertyEditorSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EvaluationSupport extends PropertyEditorSupport {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public String getAsText() {
      Object value = getValue();
      return value == null ? "" : ((Evaluation)value).getCourseId() + ":" + ((Evaluation)value).getTopicTitle();
    }

    @Override
    public void setAsText(String id) {
        Evaluation evaluation = Evaluation.getById(id);
        
        if (evaluation == null) {
          throw new IllegalArgumentException("Invalid id for evaluation: " + id);
        }
        setValue(evaluation);
    }    
}
