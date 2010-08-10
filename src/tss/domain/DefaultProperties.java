package tss.domain;

/**
 * defining default value of game
 * @author Dong Won Kim
 */
import java.io.*;

public class DefaultProperties {
    public static String appRoot;
    public static String appConfigBase;

//    public static String APP_ROOT = File.separator + "var" +  File.separator + "www" + File.separator + "thg" + File.separator;
    public static String INTRODUCTION_XML = "introduction.xml";
    public static String ORIENTATION_XML = "orientation.xml";
    public static String EVALUATION_XML = "evaluation.xml";
    public static String SUB_TOPIC_XML = "sub-topics.xml";
    public static String GUIDE_XML = "guides.xml";
    public static String STATION_XML = "stations.xml";

    public static String NPC_XML = "npc.xml";
//    public static String CONFIG_DIR = APP_ROOT + "config" + File.separator;

    //PLACE XML
    public static String PLACE_XML = "place.xml";
    public static String PLACE_QUALIFIED_NAME = "places";
    public static String PLACE_FIRST_ELEMENT_NAME = "place";
    public static String[] PLACE_FIRST_ELEMENT_ATTRIBUTES = new String[] {"name", "level", "x", "y", "file"};

    //TOOL XML
    public static String TOOL_XML = "tool.xml";
    public static String TOOL_QUALIFIED_NAME = "tools";
    public static String TOOL_FIRST_ELEMENT_NAME = "tool";
    public static String[] TOOL_FIRST_ELEMENT_ATTRIBUTES = new String[] {"name", "file"};

    //ARMOR XML
    public static String ARMOR_XML = "armor.xml";
    public static String ARMOR_QUALIFIED_NAME = "armors";
    public static String ARMOR_FIRST_ELEMENT_NAME = "armor";
    public static String[] ARMOR_FIRST_ELEMENT_ATTRIBUTES = new String[] {"name", "file"};

    //WEAPON XML
    public static String WEAPON_XML = "weapon.xml";
    public static String WEAPON_QUALIFIED_NAME = "weapons";
    public static String WEAPON_FIRST_ELEMENT_NAME = "weapon";
    public static String[] WEAPON_FIRST_ELEMENT_ATTRIBUTES = new String[] {"name", "file"};
    
    public DefaultProperties() {

    }

    public void setAppRoot(String appRoot) {
        DefaultProperties.appRoot = appRoot;
    }

    static public String getAppRoot() {
        return appRoot;
    }

    static public String getAppConfigBase() {
        if (appConfigBase == null) {
            appConfigBase = getAppRoot() + File.separator + "config";
        }

        return appConfigBase;
    }

}
