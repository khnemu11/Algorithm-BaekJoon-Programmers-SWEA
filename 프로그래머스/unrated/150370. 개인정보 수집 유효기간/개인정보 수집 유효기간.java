import java.util.*;
import java.time.*;
import java.time.format.*;


class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> result = new ArrayList<>();
        
        Map<String,Integer> termMap = setMap(terms);
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate now = LocalDate.parse(today,format);
       
        int idx = 1;
       
        for(String privacy : privacies){
            String [] splited = privacy.split(" ");
            String key = splited[1];
            
            LocalDate time = LocalDate.parse(splited[0],format);
            LocalDate limit = time.plusMonths(termMap.get(key));
           
            if(limit.compareTo(now)<=0){
                result.add(idx);
            }
            
            idx++;
        }
        int answer [] = new int[result.size()];
        
        for(int i=0;i<answer.length;i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    public Map<String,Integer> setMap(String [] terms){
        Map<String,Integer> termMap = new HashMap<>();
        
        for(String term : terms){
            String [] splited = term.split(" ");
            termMap.put(splited[0],Integer.valueOf(splited[1]));
        }
        
        return termMap;
    }
    
}