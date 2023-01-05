import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String,Integer> players = new HashMap<>();
        
        for(String person :completion){
            players.put(person, players.getOrDefault(person,0)+1);     
        }
        for(String person :participant){
            if(players.get(person)==null || players.get(person)==0){
                answer = person;
                break;
            } 
            players.put(person, players.get(person)-1);
        }
        
        
        return answer;
    }
}