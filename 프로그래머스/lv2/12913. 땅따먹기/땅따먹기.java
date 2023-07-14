import java.util.*;
// 현재 최대값 = 이전 최대값 + 현재 열 => DP
// 시간 복잡도 = 모든 행의 개수 * 각 열당 최대값 연산 * 모든 열의 개수 = 행의 개수 * 열의 개수 ^2
//             = 100,000 * 4^4 = 6,400,000 
class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int maxDp[][] = new int[land.length+1][4];
        
        //현재 행
        for(int i=1;i<maxDp.length;i++){
            //최대값을 설정할 열
            for(int j=0;j<land[i-1].length;j++){
                int max = 0;
                //최대값 탐색
                for(int k=0;k<land[i-1].length;k++){
                    if(j == k){
                        continue;
                    }
                    max = Math.max(maxDp[i-1][k],max);
                }
                maxDp[i][j] = max +land[i-1][j];
            }    
        }
      
        return Arrays.stream(maxDp[maxDp.length-1]).max().getAsInt();
    }
}