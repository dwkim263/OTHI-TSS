package tss.domain;

/**
 *
 * @author Steve
 */
import tss.domain.xml.ArmorXML;
import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Armor {
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static Map<String, Armor> armors = new HashMap<String, Armor>();
        
    private String name;
    private String filename;
    
    private Armor(String name, String filename){
        this.name = name;
        this.filename = filename;
        armors.put(name, this);
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

    public static Map<String, Armor> getArmors() {
        if (armors.isEmpty()) {
            setArmors();
        }
        return armors;
    }

    public static void setArmors() {

        File[] directories = (new File(DefaultProperties.getAppRoot())).listFiles();
        for (File directory: directories) {
            if (directory.isDirectory()) {
                ArmorXML armorXml = null;

                File[] files = directory.listFiles();
                for (File file: files) {
                    if (file.getName().equals(DefaultProperties.ARMOR_XML))
                        armorXml = new ArmorXML(file.getAbsolutePath());
                }

                if (armorXml != null) {
                    Map <String, Object> armorProperty = armorXml.getProperties();
                    Iterator it = armorProperty.keySet().iterator();
                    while(it.hasNext()) {
                        String name = (String) it.next();
                        Map <String, String> armor = (Map <String, String>) armorProperty.get(name);
                        new Armor((String) armor.get("name"), (String) armor.get("file"));
                    }
                }
            }
        }
    }


    public static void setArmors(Map<String, Armor> armors) {
        Armor.armors = armors;
    }

    public static Armor getById(String id) {
        return armors.get(id);
    }    
}
