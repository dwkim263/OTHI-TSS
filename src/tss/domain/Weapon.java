package tss.domain;

/**
 *
 * @author Steve
 */
import tss.domain.xml.WeaponXML;
import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Weapon {
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static Map<String, Weapon> weapons = new HashMap<String, Weapon>();
        
    private String name;
    private String filename;
    
    private Weapon(String name, String filename){
        this.name = name;
        this.filename = filename;
        weapons.put(name, this);
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

    public static Map<String, Weapon> getWeapons() {
        if (weapons.isEmpty()) {
            setWeapons();
        }
        return weapons;
    }

    public static void setWeapons() {

        File[] directories = (new File(DefaultProperties.getAppRoot())).listFiles();
        for (File directory: directories) {
            if (directory.isDirectory()) {
                WeaponXML weaponXml = null;

                File[] files = directory.listFiles();
                for (File file: files) {
                    if (file.getName().equals(DefaultProperties.WEAPON_XML))
                        weaponXml = new WeaponXML(file.getAbsolutePath());
                }

                if (weaponXml != null) {
                    Map <String, Object> weaponProperty = weaponXml.getProperties();
                    Iterator it = weaponProperty.keySet().iterator();
                    while(it.hasNext()) {
                        String name = (String) it.next();
                        Map <String, String> weapon = (Map <String, String>) weaponProperty.get(name);
                        new Weapon((String) weapon.get("name"), (String) weapon.get("file"));
                    }
                }
            }
        }
    }


    public static void setWeapons(Map<String, Weapon> weapons) {
        Weapon.weapons = weapons;
    }

    public static Weapon getById(String id) {
        return weapons.get(id);
    }    
}
