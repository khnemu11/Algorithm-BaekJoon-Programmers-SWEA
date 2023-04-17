import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

class Solution {
    public String[] solution(String[][] plans) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        PriorityQueue<Process> pq = new PriorityQueue<>();
        Stack<Process> stack = new Stack<>();
        String result[] = new String[plans.length];
        int idx=0;
        
        for(int i=0;i<plans.length;i++){
            pq.add(new Process(plans[i][0],LocalTime.parse(plans[i][1],format),Integer.valueOf(plans[i][2])));
        }
        
        LocalTime currTime = pq.peek().start;
        
        while(!pq.isEmpty()){
            Process process = pq.poll();
            // System.out.println("다음 과제 : "+process.name);
            while(!stack.isEmpty()){
                // System.out.println("현재시간 : "+currTime);
                Process recentProcess = stack.pop();
                int differ =  (int)ChronoUnit.MINUTES.between(currTime,process.start);
                // System.out.println("가능한 처리 시간 : "+Math.min(recentProcess.playtime,differ+recentProcess.spendtime));
                int time=0;
                if(recentProcess.spendtime + differ >= recentProcess.playtime){
                    time = recentProcess.playtime- recentProcess.spendtime;
                    result[idx++] =  recentProcess.name;
                    currTime = currTime.plusMinutes(time); 
                }else{
                    time = differ;
                    recentProcess.spendtime = differ+recentProcess.spendtime;
                    stack.add(recentProcess);
                    currTime = currTime.plusMinutes(time); 
                    break;
                }
                
                if(currTime.equals(process.start)){
                    break;
                }
            }
            currTime = process.start;
            stack.add(process);
        }
        
        while(!stack.isEmpty()){
            result[idx++] = stack.pop().name;
        }
        
        return result;
    }
}
class Process implements Comparable<Process>{
    String name;
    LocalTime start;
    int playtime;
    int spendtime;
    
    public String toString(){
        return start+" ";
    }
    
    public Process(String name,LocalTime start,int playtime){
        this.name=name;
        this.start =start;
        this.playtime=playtime;
    }
    
    public int compareTo(Process o){
        if(this.start.isBefore(o.start)){
            return -1;
        }else{
            return 1;
        }
    }
}