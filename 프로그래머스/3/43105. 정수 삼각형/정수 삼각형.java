import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int dp[][] = new int [triangle.length][triangle.length];
        dp[0][0] = triangle[0][0]; //초기값
        
        //2번재 줄 부터 시작
        for(int length=1;length<triangle.length;length++){
            //삼각형은 현재 행의 길이(length+1)까지 판단하면 됨
            for(int i=0;i<=length;i++){
                int beforeLeft = i-1 < 0 ? 0 : dp[length-1][i-1] ;
                int beforeRight = i > length -1 ? 0 : dp[length-1][i];
                
                //이전 왼쪽 값과 오른쪽 값 중 최대값을 더한 값을 저장
                dp[length][i] = Math.max(beforeLeft,beforeRight) +triangle[length][i];
            }   
        }
        
        //가장 아래 줄에서 최대값 추출
        int max = Arrays.stream(dp[triangle.length-1]).max().getAsInt();
        
        return max;
    }
}