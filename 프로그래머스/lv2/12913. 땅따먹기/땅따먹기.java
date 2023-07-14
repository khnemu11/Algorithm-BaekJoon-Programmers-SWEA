import java.util.*;
//4개밖에 안되니까 if문으로 처리
class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int maxDp[][] = new int[land.length+1][4];

        for(int i=1;i<maxDp.length;i++){
            for(int j=0;j<land[i-1].length;j++){
                int max = 0;
                for(int k=0;k<land[i-1].length;k++){
                    if(j == k){
                        continue;
                    }
                    max = Math.max(maxDp[i-1][k],max);
                }
                maxDp[i][j] = max +land[i-1][j];
                // System.out.println(Arrays.toString(maxDp[j]));
            }    
        }
      
        return Arrays.stream(maxDp[maxDp.length-1]).max().getAsInt();
    }
}