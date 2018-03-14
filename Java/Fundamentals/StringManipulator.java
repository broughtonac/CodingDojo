public class StringManipulator {
    public String trimAndConcat(String s, String s1) {
        s.replaceAll("\\s+", "");
        s1.replaceAll("\\s+", "");
        return(s + s1);
    }
    public Integer getIndexOrNull(String s, char c) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c) {
                return(i);
            }
        }
        return(null);
    }
    public Integer getIndexOrNull(String s, String sub) {
        for(int i = 0; i < s.length(); i++) {
            boolean flag = true;
            int k = i;
            for(int j = 0; j < sub.length(); j++) {
                if(s.charAt(k) != sub.charAt(j)) {
                    flag = false;
                    break;
                }
                k++;
            }
            if(flag) {
                return(i);
            }
        }
        return(null);
    }
    public String concatSubstring(String s, int from, int to, String s2) {
        String slice = "";
        for(int i = from; i < to; i++) {
            slice = slice + s.charAt(i);
        }
        return(slice + s2);
    }
}