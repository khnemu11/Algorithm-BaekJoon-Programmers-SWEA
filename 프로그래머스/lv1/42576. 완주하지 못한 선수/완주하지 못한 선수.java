import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        Map<String, Integer> name = new HashMap<>();
        
        for (int i = 0; i < participant.length; i++) {
            if (name.get(participant[i]) == null) {
                name.put(participant[i], 1);    
            } else {
                int num = name.get(participant[i]) + 1;
                name.put(participant[i], num);
            }
        }
        
        for (int i = 0; i < completion.length; i++) {
            int num = name.get(completion[i]) - 1;
            name.replace(completion[i], num);
        }
        
        String answer = "";
        for (String nm : name.keySet() ) {
            int num = name.get(nm);
            if (num != 0) {
                answer = nm;
                break;
            }
        }
        
        return answer;
    }
}