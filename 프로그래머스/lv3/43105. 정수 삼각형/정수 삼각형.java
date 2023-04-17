import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int dp[][] = new int[triangle.length][triangle[triangle.length-1].length];
        dp[0][0] = triangle[0][0];
        
        //오른쪽 최대값
        for(int i=1;i<triangle.length;i++){
             dp[i][0] = triangle[i][0] + dp[i-1][0];
             for(int j=1;j<triangle[i].length-1;j++){
                dp[i][j] = triangle[i][j]+Math.max(dp[i-1][j],dp[i-1][j-1]);   
             }
             dp[i][triangle[i].length-1] = triangle[i][triangle[i].length-1] + dp[i-1][triangle[i].length-2];
        }
        
        int max = 0;
        
        for(int j=0;j<triangle[triangle.length-1].length;j++){
            max = Math.max(max,dp[triangle.length-1][j]);
        }
        
        return max;
    }
}