import java.util.*;
class Solution {
    long dp[];
    boolean visited[];
    
    public long solution(int n) {
        dp = new long[2000+1];
        visited = new boolean[2000+1];    
        
        dp[1]=1;
        dp[2]=2;
        visited[1]=true;
        visited[2]=true;
        
        long answer = getCase(n);
        
        return answer;
    }
    
    public long getCase(int n){
        if(visited[n]){
            return dp[n];
        }
        else if(n>0){
            visited[n]=true;
            dp [n] = (getCase(n-1) + getCase(n-2)) % 1234567;
            return dp[n];
        }else{
            return 0;
        }
    }
}