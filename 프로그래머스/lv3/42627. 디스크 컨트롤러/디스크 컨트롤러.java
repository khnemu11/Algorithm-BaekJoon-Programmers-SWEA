import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Process> sortedJobs = new PriorityQueue<>(jobs.length, (o1,o2)->{
            if(o1.requestTime == o2.requestTime){
                return o1.val - o2.val;
            }
            return o1.requestTime - o2.requestTime;
        });
        
        for(int i=0;i<jobs.length;i++){
            sortedJobs.add(new Process(jobs[i][0],jobs[i][1]));
        }
        
        int currTime = 0;
        ArrayList<Integer> spendTimeList= new ArrayList<>();
        
        PriorityQueue<Process> priorityJobs = new PriorityQueue<>(sortedJobs.size(), (o1,o2)->o1.val - o2.val);
        
        while(!sortedJobs.isEmpty()){
            while(!sortedJobs.isEmpty() && currTime >= sortedJobs.peek().requestTime){
                priorityJobs.add(sortedJobs.poll());
            }
            
            if(priorityJobs.isEmpty()){ // 현재시간으로 작업 처리 불가능-> 가장 요청시간이 짧은 작업을 가져온다
                Process process = sortedJobs.poll();
                currTime = process.requestTime;
                priorityJobs.add(process);
            }
            
            Process process = priorityJobs.poll();
            currTime = currTime + process.val;
            spendTimeList.add( currTime-process.requestTime);
            System.out.println(currTime);
        }
        
        while(!priorityJobs.isEmpty()){            
            Process process = priorityJobs.poll();
            
            System.out.println(process.requestTime+" : "+process.val);
            currTime = currTime + process.val;
            spendTimeList.add(currTime-process.requestTime);
            System.out.println(currTime);
        }
        
        
        int answer = (int)spendTimeList.stream().mapToInt(x->Integer.valueOf(x)).average().getAsDouble();
        return answer;
    }
}
class Process{
    int requestTime;
    int val;
    
    public Process(int requestTime, int val){
        this.requestTime = requestTime;
        this.val =val;
    }
    public String toString(){
        return requestTime +" : "+val;
    }
    
}