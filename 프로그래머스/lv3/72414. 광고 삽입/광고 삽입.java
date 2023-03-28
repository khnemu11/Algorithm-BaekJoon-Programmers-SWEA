import java.util.*;

class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTime = parseTime(play_time);
        int advTime = parseTime(adv_time);
        int sum [] = new int[playTime+1];
        
        for(int i=0;i<logs.length;i++){
            String splitedLog[] = logs[i].split("-");
            int start = parseTime(splitedLog[0]);
            int end = parseTime(splitedLog[1]);
        
            for(int j=start;j<end;j++){
                sum[j]++;
            }
        }   
        
        long curr = 0;
        long time = 0;
        for(int i=0;i<advTime;i++){
            curr+=sum[i];
        }
        long max = curr;
        for(int start=1;start+advTime-1<playTime;start++){
            curr = curr - sum[start-1] + sum[start+advTime-1];
            if(max < curr){
                time = start;
                max = curr;
            }
        }
        
        return parseInt(time);
    }
    public int parseTime(String time){
        String splited[] = time.split(":");
        int result = Integer.valueOf(splited[0])*60*60  +Integer.valueOf(splited[1])*60 + Integer.valueOf(splited[2]);
        return result;
    } 
     public String parseInt(long time){
        String hour = String.valueOf(time/(60*60));
        time = time %(60*60);
        hour = hour.length() ==1 ? "0"+hour : hour;
         
        String minute = String.valueOf(time/60);
        minute = minute.length() ==1 ? "0"+minute : minute;
        time = time%60;
         
        String second = String.valueOf(time);
        second = second.length() ==1 ? "0"+second : second;
        
         
        return hour+":"+minute+":"+second;
    } 
}