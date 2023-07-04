import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        m = m.replace("C#","c");
        m = m.replace("D#","d");
        m = m.replace("F#","f");
        m = m.replace("G#","g");
        m = m.replace("A#","a");
        
        PriorityQueue<ListenedMusic> musicQueue = new PriorityQueue<>();
        
        for(int i=0;i<musicinfos.length;i++){
            String [] splited = musicinfos[i].split(",");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime startTime = LocalTime.parse(splited[0],formatter);
            LocalTime endTime = LocalTime.parse(splited[1],formatter);
          
            splited[3] = splited[3].replace("C#","c");
            splited[3] = splited[3].replace("D#","d");
            splited[3] = splited[3].replace("F#","f");
            splited[3] = splited[3].replace("G#","g");
            splited[3] = splited[3].replace("A#","a");
            
            long playTime = ChronoUnit.MINUTES.between(startTime, endTime);
            String music = "";
            
            for(int j=0;j<playTime/splited[3].length();j++){
                music += splited[3];
            }
            
            if(playTime%splited[3].length() >0){
                music += splited[3].substring(0,(int)playTime%splited[3].length());
            }
            
            if(music.contains(m)){
                musicQueue.add(new ListenedMusic(splited[2],playTime,i));
            }
        }
        
        if(musicQueue.isEmpty()){
            return "(None)";    
        }
        ListenedMusic targetMusic = musicQueue.poll();
        return targetMusic.name;
    }
}
class ListenedMusic implements Comparable<ListenedMusic>{
    String name;
    long playTime;
    int sequence;
    
    public ListenedMusic(String name, long playTime,int sequence){
        this.name = name;
        this.playTime = playTime;
        this.sequence = sequence;
    }
    
    public int compareTo(ListenedMusic o){
        if(this.playTime == o.playTime){
            return this.sequence - o.sequence;
        }
        return (int)o.playTime - (int)this.playTime;
    }
}   