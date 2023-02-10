import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {      
        HashMap<Character, Integer> map = new HashMap<>();
        
        map.put('R',0);
        map.put('T',0);
        map.put('C',0);
        map.put('F',0);
        map.put('J',0);
        map.put('M',0);
        map.put('A',0);
        map.put('N',0);
        
        for(int i=0;i<survey.length;i++){
            if(choices[i] > 3){
                map.put(survey[i].charAt(1),map.get(survey[i].charAt(1))+choices[i] -4);
            }else{
                 map.put(survey[i].charAt(0),map.get(survey[i].charAt(0))+4-choices[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        
        sb.append(map.get('T') > map.get('R') ? "T" : "R");
        sb.append(map.get('F') > map.get('C') ? "F" : "C");
        sb.append(map.get('M') > map.get('J') ? "M" : "J");
        sb.append(map.get('N') > map.get('A') ? "N" : "A");
        
        return sb.toString();
    }
}