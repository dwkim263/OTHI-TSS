package tss.domain;

/**
 *
 * @author Steve
 */
import tss.domain.xml.ToolXML;
import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Tool {
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static Map<String, Tool> tools = new HashMap<String, Tool>();
        
    private String name;
    private String filename;
    
    private Tool(String name, String filename){
        this.name = name;
        this.filename = filename;
        tools.put(name, this);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map<String, Tool> getTools() {
        if (tools.isEmpty()) {
            setTools();
        }
        return tools;
    }

    public static void setTools() {

        File[] directories = (new File(DefaultProperties.getAppRoot())).listFiles();
        for (File directory: directories) {
            if (directory.isDirectory()) {
                ToolXML toolXml = null;

                File[] files = directory.listFiles();
                for (File file: files) {
                    if (file.getName().equals(DefaultProperties.TOOL_XML))
                        toolXml = new ToolXML(file.getAbsolutePath());
                }

                if (toolXml != null) {
                    Map <String, Object> toolProperty = toolXml.getProperties();
                    Iterator it = toolProperty.keySet().iterator();
                    while(it.hasNext()) {
                        String name = (String) it.next();
                        Map <String, String> tool = (Map <String, String>) toolProperty.get(name);
                        new Tool((String) tool.get("name"), (String) tool.get("file"));
                    }
                }
            }
        }
    }


    public static void setTools(Map<String, Tool> tools) {
        Tool.tools = tools;
    }

    public static Tool getById(String id) {
        return tools.get(id);
    }    
}
