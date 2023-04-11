import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int priorityCnt[] = new int[10];
        priorityCnt[0]=Integer.MAX_VALUE;
        int seq=1;
        int priority = 9;
        
        Queue<Print>q = new LinkedList<>();
        
        for(int i=0;i<priorities.length;i++){
            priorityCnt[priorities[i]]++;
            q.add(new Print(i,priorities[i]));
        }
        
        // System.out.println(q);
        
        while(!q.isEmpty()){
             while(priorityCnt[priority] == 0){
                priority--;
            }
            
            Print print = q.poll();
            
            while(print.priority < priority){
                q.add(print);
                print = q.poll();
            }
            if(print.location == location){
                break;
            }
            
            priorityCnt[priority]--;
            seq++;
        }
        
        return seq;
    }
}
class Print{
    int location;
    int priority;
    
    public Print(int location, int priority){
        this.location = location;
        this.priority = priority;
    }
    
    public String toString(){
        return location+" : "+priority;
    }
}