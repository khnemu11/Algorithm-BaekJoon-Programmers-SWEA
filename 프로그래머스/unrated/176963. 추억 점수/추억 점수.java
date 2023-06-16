import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String,Integer> yearningMap = new HashMap<>(); //<사람 이름, 그리움 점수> 맵
        
        //사람 당 그리움 점수 맵 초기화
        for(int i=0;i<name.length;i++){
           yearningMap.put(name[i],yearning[i]); 
        }
        
        //그리움 점수 합이 담긴 배열
        int answer[] = new int[photo.length];
        
        //그리움 점수을 합산하여 answer에 추가
        for(int i=0;i<photo.length;i++){
            int sum = 0;
            for(int j=0;j<photo[i].length;j++){
                //이름이 없으면 0점으로
                sum += yearningMap.getOrDefault(photo[i][j],0);
            }   
            answer[i]=sum;
        }
        
        return answer;
    }
}