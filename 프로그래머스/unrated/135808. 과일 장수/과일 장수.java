import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        Integer arrInteger[]= Arrays.stream(score).boxed().toArray(Integer[]::new);
        Arrays.sort(arrInteger,Collections.reverseOrder());
        
        for(int i=m-1; i<arrInteger.length;i+=m){
            answer +=arrInteger[i]*m;
        }
        
        return answer;
    }
}