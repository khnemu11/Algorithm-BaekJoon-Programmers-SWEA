import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int map[][] = new int[n+1][m+1];
       
        for(int i=0;i<puddles.length;i++){
            if(puddles[i].length>0){
                map[puddles[i][1]][puddles[i][0]]=1;
            }
        }
        int count[][] = new int[n+1][m+1];
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(i==1 && j==1){
                    count[i][j]=1;
                    continue;
                }
                int up = map[i-1][j]==1 ? 0 : count[i-1][j];
                int left = map[i][j-1]==1 ? 0 : count[i][j-1];
                count[i][j] = (up+left)%1_000_000_007;
            }
        }
        return count[n][m];
    }
}
