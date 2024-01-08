import java.util.*;

class Solution {
    final int EXIST = 1;
    final int MODULAR = 10_007;
    
    public int solution(int n, int[] tops) {
        int answer = 0;
        int[] dp = new int[n*2+2];
        dp[0]=1;        
        dp[1]=1;
               
        for(int i=2;i<dp.length;i++){
            dp[i] = (dp[i-2]%MODULAR+dp[i-1]%MODULAR)%MODULAR;
            
            if(i%2 == 0 && tops[i/2-1] == EXIST){
                dp[i] = (dp[i]%MODULAR + dp[i-1]%MODULAR)%MODULAR;
            }
        }
        return dp[dp.length-1];
    }
}