import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        int dp[] = new int [n+1];
        int M = 1_000_000_007;
        
        Arrays.sort(money);
        
        for(int m : money){
            dp[m] = dp[m] + 1;
            for(int curr=m+1; curr<dp.length;curr++){
                dp[curr] = dp[curr] + dp[curr - m];
            }   
        }
        
        
        return dp[n];
    }
}