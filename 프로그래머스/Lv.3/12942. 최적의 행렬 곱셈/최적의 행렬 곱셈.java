/*
    점화식)
    
    dp[start][end] = Math.min(dp[start][mid] + dp[mid][end] + A[start]*A[mid]*A[end],dp[start][end])  (mid > start && mid < end)
*/
import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int answer = 0;
        //구성요소를 1차원 배열로 변경해 인덱스 접근을 편하게 변경
        int[] mat = new int[matrix_sizes.length + 1];
        mat[0] = matrix_sizes[0][0];
    
        for(int i=0;i<matrix_sizes.length;i++){
            mat[i+1] = matrix_sizes[i][1];
        }
       
        //최소 곱셈 배열 초기화
        int[][] dp = new int[matrix_sizes.length+1][matrix_sizes.length+1];
        
        for(int i=0;i<dp.length-1;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        //기본값 초기화
        for(int i=0;i<dp.length-1;i++){
            dp[i][i+1] = 0;
        }
        
        //행렬의 길이, 시작점, 중간점을 기준으로 해당 길이의 최소 곱셈 연산 횟수 저장
        for(int len =2;len<mat.length;len++){
            for(int start=0;start+len<dp.length;start++){
                for(int mid = start+1;mid < start+len;mid++){
                    dp[start][start+len] = Math.min(dp[start][start+len], 
                    dp[start][mid]+dp[mid][start+len] + mat[start]*mat[mid]*mat[start+len]);
                }
            }
        }
        
        return dp[0][mat.length-1];
    }
}