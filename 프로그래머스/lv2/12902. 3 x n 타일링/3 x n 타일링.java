import java.util.*;

class Solution {
    public long solution(int n) {
        int DIVIDER = 1_000_000_007;
        
        if(n%2==1){
            return 0;
        }
        
        long dp[]= new long[n+1];
        for(int i=2;i<=n;i+=2){
            if(i==2){
                dp[i]=3;
            }else if(i==4){
                dp[i]=11;
            }else{
                dp[i]=(dp[i-2]*4%DIVIDER-dp[i-4]%DIVIDER+DIVIDER)%DIVIDER;
            }
        }
        
        return dp[n];
    }
}