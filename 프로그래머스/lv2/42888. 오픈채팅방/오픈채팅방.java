import java.util.*;

class Solution {
    public String[] solution(String[] records) {
        HashMap<String,String> map = new HashMap<>();
        Queue<String> logs = new LinkedList<>();
        
        for(String record : records){
            String parse[] = record.split(" ");
            if(parse[0].equals("Change")){
                map.put(parse[1],parse[2]);
            }else if(parse[0].equals("Enter")){
                map.put(parse[1],parse[2]);
                logs.add(parse[0] + " "+parse[1]);
            }else{
                logs.add(parse[0] + " "+parse[1]);
            }
        }
        
        String[] answer = new String[logs.size()];
        int idx=0 ;
        while(!logs.isEmpty()){
            String log = logs.poll();
            String parse[] = log.split(" ");
            String log_str="";

            if(parse[0].equals("Enter")){
                log_str = map.get(parse[1])+"님이 들어왔습니다.";
            }else{
                log_str = map.get(parse[1])+"님이 나갔습니다.";
            }
            
            answer[idx++]= log_str;
        }
       
        return answer;
    }
}