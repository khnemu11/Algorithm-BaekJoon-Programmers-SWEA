/*
    안되는 경우 : n > s 
    되는 경우 : 
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