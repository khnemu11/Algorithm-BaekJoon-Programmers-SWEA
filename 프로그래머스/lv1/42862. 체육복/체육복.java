import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int clothes [] = new int[n+1];
        
        Arrays.fill(clothes,1);
        
        for(int i=0;i<lost.length;i++){
            clothes[lost[i]]--;
        }
        for(int i=0;i<reserve.length;i++){
            clothes[reserve[i]]++;
        }
        for(int i=1;i<clothes.length;i++){
            if(clothes[i]==0){
                continue;
            }
            if(clothes[i]==2){
                if(i-1 >=0 && clothes[i-1]==0){
                    clothes[i]--;
                    clothes[i-1]++;
                }
                else if(i+1<clothes.length && clothes[i+1]==0){
                    clothes[i]--;
                    clothes[i+1]++;
                }          
            } 
        }
        
        for(int i=1;i<clothes.length;i++){
            if(clothes[i]>0){
                answer++;
            }
        }
        
        return answer;
    }
}