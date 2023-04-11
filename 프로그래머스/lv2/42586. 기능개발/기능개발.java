import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        PriorityQueue<Integer>pq = new PriorityQueue<>(Collections.reverseOrder());        
        ArrayList<Integer>result = new ArrayList<>();
        
        for(int i=0;i<progresses.length;i++){
            int rest = 100 - progresses[i];
            int time = rest / speeds[i];
            
            if(rest % speeds[i] > 0 ){
                time++;
            }
            
            if(pq.isEmpty()){
                pq.add(time);
            }else if(pq.peek() < time){
                result.add(pq.size());
                pq = new PriorityQueue<>(Collections.reverseOrder());      
                pq.add(time);
            }else{
                pq.add(time);
            }
        }
        if(!pq.isEmpty()){
            result.add(pq.size());
        }

        int answer[] = new int[result.size()];
        for(int i=0;i<result.size();i++){
            answer[i]=result.get(i);
        }
        
        return answer;
    }
}