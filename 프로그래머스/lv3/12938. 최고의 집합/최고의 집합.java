/*
    안되는 경우 : n > s 
    가장 큰 경우 : 모두가 비등한 값을 가진 경우
    
    ex) 1*8 < 2*7 < 3*6 < 4*5
    
    모두 동일하게 값을 가지고 나머지 값을 오른차순이 되도록 1개씩 배분한다.
*/

import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if(n > s){
            return new int[]{-1};
        }
        
        int[] answer = new int[n];
        
        int val = s/n;
        int rest = s%n;
        
        Arrays.fill(answer,val);
            
        for(int i=0;i<rest;i++){
            answer[answer.length-(i+1)]++;
        }
             
        return answer;
    }
}