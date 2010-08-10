package tss.domain;

/**
 *
 * @author Steve
 */
import tss.domain.xml.StationXML;
import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PlaceNickName {
    protected final Log logger = LogFactory.getLog(getClass());   
    
    public static String getPlaceName(String topic, String place) {

        File[] directories = (new File(DefaultProperties.getAppRoot())).listFiles();
        for (File directory: directories) {
            if (directory.isDirectory()) {
                StationXML stationXML = null;

                File[] files = directory.listFiles();
                for (File file: files) {
                    if (file.getName().equals(DefaultProperties.STATION_XML))
                        stationXML = new StationXML(file.getAbsolutePath());
                }

                if (stationXML != null) {
                    Map <String, Object> stationProperty = stationXML.getProperties();
                    Iterator it = stationProperty.keySet().iterator();
                    while(it.hasNext()) {
                        String name = (String) it.next();
                        Map <String, String> station = (Map <String, String>) stationProperty.get(name);
                        if (topic.contains((String) station.get("topic")) &&
                                place.contains((String) station.get("place")))
                                return (String) station.get("place_nick_name");

                    }
                }
            }
        }
        return null;
    }
}
