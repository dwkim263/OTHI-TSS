package tss.domain.xml;

/**
 * 
 * @author Dong Won (Steven) Kim
 * 
 */
import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XmlFile implements Serializable {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    private boolean existed = false;
    private String fileName = null;

    public XmlFile (){
        
    }
    
    public XmlFile (String fileName){
        setExisted(fileName);
        setFileName(fileName);                
        makeDir(fileName);
    }

    public boolean isExisted() {
        return existed;
    }

    public void setExisted(boolean existed) {       
        this.existed = existed;
    }
        
    public void setExisted(String fileName) {       
        existed = new File(fileName).exists();
    }
            
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setOutStream(String fileName) {

    }

    public OutputStream getOutStream() {
        OutputStream outStream = null;
        try {            
            outStream = new FileOutputStream(getFileName());                            
        } catch (IOException ex) {
            logger.error(ex);            
        }        
        return outStream;
    }

    public void makeDir(String fileName) {
        String dirNames = fileName.substring(0, fileName.lastIndexOf("/"));
        File dir = new File(dirNames);
        if (!dir.exists()){
            boolean success = dir.mkdirs();
          
            if (success) {
              logger.info("Directories: " + dirNames + " created");
            } else {
              logger.error("Directories: " + dirNames + " cannot be created"); 
            }
        }
    }        
}
