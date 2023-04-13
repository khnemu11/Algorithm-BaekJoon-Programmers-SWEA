import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        //가장 먼저 요청한 작업부터 정렬
        //이때 요청한 시간이 같아면 작업량이 작은것 부터
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
            while(!sortedJobs.isEmpty() && currTime >= sortedJobs.peek().requestTime){  //현재 시간 이전에 요청한 작업을 꺼내옴
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
        }
        //남은 대기열에 있는 작업 처리
        while(!priorityJobs.isEmpty()){            
            Process process = priorityJobs.poll();
            currTime = currTime + process.val;
            spendTimeList.add(currTime-process.requestTime);
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
