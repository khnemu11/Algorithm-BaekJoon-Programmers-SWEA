import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        PriorityQueue<Time> crewQueue = new PriorityQueue<>();
        for(String time : timetable){
            String timeSplited [] = time.split(":");
            crewQueue.add(new Time(timeSplited[0],timeSplited[1]));
        }
        
        Time bus = new Time("9","0");
        Time lastCrew = null;
        
        int total = 0;
        int busCount = 0;
        int crewCount = 0;
        
        while(!crewQueue.isEmpty() && busCount < n){
            crewCount = 0;
            
            for(int i=0;i<m;i++){
                if(crewQueue.isEmpty()){
                    break;
                }
                Time curr = crewQueue.peek();
                
                if(curr.compareTo(bus) > 0 || crewCount == m){
                    break;   
                }
                
                lastCrew = crewQueue.poll();
                crewCount++;
            }
            bus.addMinute(t);
            busCount++;
            total++;
        }
        // System.out.println("last crew count : "+crewCount);
        // System.out.println("last bus count : "+busCount);
        
        //만약 마지막 출발 버스가 남은 자리가 있는경우
        if(crewCount < m){
        
            bus.addMinute(-t);
            // System.out.println(bus.toString());
            answer = bus.toString();
        }
        //마지막 버스에 탈 수 있는경우
        else if(busCount < n){
            // System.out.println(bus.toString());
            answer = bus.toString();
        }    
        
        //마지막 버스에 탈 수 없는 경우
        else{
            //사람들이 너무 늦게와서 아무도 못탄 경우
            if(lastCrew==null){
                bus.addMinute(-t);
                // System.out.println(bus.toString());
                answer = bus.toString();
            }
            //막차를 뺏긴 경우
            else{
                lastCrew.addMinute(-1);
                // System.out.println(lastCrew.toString());
                  answer = lastCrew.toString();
            }
        }
        
        return answer;
    }
}
class Time implements Comparable<Time>{
    int hour;
    int minute;
    
    public Time(String hour, String minute){
        this.hour = Integer.valueOf(hour);
        this.minute = Integer.valueOf(minute);
    }
    
    @Override
    public int compareTo(Time o){
        if(this.hour == o.hour){
            return this.minute - o.minute;
        }
        
        return this.hour - o.hour;
    }
    
    @Override
    public String toString(){
        String hour = this.hour < 10 ? "0"+this.hour : ""+this.hour;
        String minute = this.minute < 10 ? "0"+this.minute : ""+this.minute;
        
        return hour + ":"+ minute;
    }
    
    public void addMinute(int minute){
        this.minute = this.minute + minute;
        if(this.minute>=60){
            this.hour++;
            this.minute -=60;
        }
        else if(this.minute<0){
            this.hour--;
            this.minute +=60;
        }
    }
}