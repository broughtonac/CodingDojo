import java.util.HashMap;
import java.util.Map;

public class Hashmatique {
    public static void main(String[] args) {
        HashMap<String, String> trackList = new HashMap<String, String>();
        trackList.put("track1", "lyrics1");
        trackList.put("track2", "lyrics2");
        trackList.put("track3", "lyrics3");
        trackList.put("track4", "lyrics4");

        for(Map.Entry<String, String> x : trackList.entrySet()) {
            String v = x.getValue();
            System.out.println(v);
        }

        for(Map.Entry<String, String> x: trackList.entrySet()) {
            String k = x.getKey();
            String v = x.getValue();
            System.out.println(k + " : " + v);
        }
    }
}