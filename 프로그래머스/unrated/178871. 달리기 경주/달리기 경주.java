import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        //<플레이어 이름, 등수> 으로 저장되는 레이스 현황 맵
        Map<String,Integer> raceByNameMap = new HashMap<>();
         //<등수,플레이어 이름> 으로 저장되는 레이스 현황 맵
        Map<Integer,String> raceByGradeMap = new HashMap<>();
        
        //초기 등수 저장
        for(int i=1;i<=players.length;i++){
            raceByNameMap.put(players[i-1],i);
            raceByGradeMap.put(i,players[i-1]);    
        }
        
        for(String calling : callings){
            int grade = raceByNameMap.get(calling); //현재 등수
            String target = raceByGradeMap.get(grade-1);    //앞선 등수 플레이어 탐색
            
            //현재 플레이어 등수를 1 올리기
            raceByNameMap.put(calling,grade-1);
            raceByGradeMap.put(grade-1,calling);
            
            //앞선 플레이서 등수를 1 낮추기
            raceByNameMap.put(target,grade);
            raceByGradeMap.put(grade,target);
        }
        
        String[] answer = new String[players.length];
        
        for(int grade=1;grade<=players.length;grade++){
            answer[grade-1] = raceByGradeMap.get(grade);
        }
        
        return answer;
    }
}