import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

/*
    가장 최근의 과제를 멈추고 새로운 과제 시작/멈춰둔 과제가 많으면 가장 최근 과제부터 시작 -> 스택으로 저장
    가장 빠른 과제부터 시작 -> 시간 순으로 정렬
    
    현재 시간부터 다음 과제 시작의 시간을 이용해 남은 과제를 해결할 수 있는지 판단
    -> chronounit 활용
*/

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
            //다음 할 과제
            Process process = pq.poll();
            
            //현재시간~다음과제 시작 시간 사이에 과제를 해결할 수 있는지 판단
            while(!stack.isEmpty()){
                //가장 최근 과제 꺼내기
                Process recentProcess = stack.pop();
                //현재 시간과 다음 과제 시작 시간의 차이 계산
                int differ =  (int)ChronoUnit.MINUTES.between(currTime,process.start);
                //과제에 소요한 시간
                int time=0;
                //충분히 과제를 풀 시간이 있는 경우
                //과제를 해결하고 시간 경과 후 다른 과제도 풀 수 있는지 확인
                if(recentProcess.spendtime + differ >= recentProcess.playtime){
                    time = recentProcess.playtime- recentProcess.spendtime;
                    result[idx++] =  recentProcess.name;
                    currTime = currTime.plusMinutes(time); 
                }
                //없는경우
                //남은 시간을 모두 활용해 과제에 사용하고 더이상 과제를 풀 수 없으므로 반복문 종료
                else{
                    time = differ;
                    recentProcess.spendtime = differ+recentProcess.spendtime;
                    stack.add(recentProcess);
                    currTime = currTime.plusMinutes(time); 
                    break;
                }
                //만약 현재 시간이 과제 시간이면 더이상 기존 과제를 해결할 수 없다.
                if(currTime.equals(process.start)){
                    break;
                }
            }
            //시간이 남아도 과제 시작일 부터 과제를 시작하므로 다음 과제 시작 시간으로 설정
            currTime = process.start;
            
            //새로운 과제 시작
            stack.add(process);
        }
        
        //남은 과제를 최근에 못푼 과제부터 꺼내서 끝내기
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