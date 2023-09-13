/*
    
    1
    1   3
    1   3   3
    1   
                
*/          
import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int answer = sticker[0];

        int dp[] = new int[100_000+1];
        dp[0] = sticker[0];
        dp[1] = dp[0];
        int max = 0;
        
        for(int i=2;i<sticker.length-1;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2] + sticker[i]);
            max = Math.max(max,dp[i]);
        }
        
        answer = Math.max(answer,max);
        
        dp = new int[100_000+1];

        max = 0;
        
        for(int i=1;i<sticker.length;i++){
            if(i==1){
                dp[i] = sticker[i];
            }else{
                dp[i] = Math.max(dp[i-1],dp[i-2] + sticker[i]);    
            }
            max = Math.max(max,dp[i]);
        }
        answer = Math.max(answer,max);
        
        return answer;
    }
}