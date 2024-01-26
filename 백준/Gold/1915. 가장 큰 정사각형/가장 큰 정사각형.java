import java.io.*;
import java.util.*;


/*
*
* dp[i] = dp[i-1] + dp[i-2]
*
*
* */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] arr = new int[input[0]][input[1]];
        int[][] dp = new int[arr.length][arr[0].length];

        for(int i=0;i<arr.length;i++){
            input = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(input, 0, arr[i], 0, arr[0].length);
        }

        int max = 0;

        //한 변의 길이가 1인 정사각형 탐색
        for(int row=0;row<arr.length;row++){
            if(arr[row][0] == 1){
                dp[row][0] = 1;
                max = 1;
            }
        }
        for(int col=0;col<arr[0].length;col++){
            if(arr[0][col] == 1){
                dp[0][col] = 1;
                max = 1;
            }
        }

        //한변의 길이가 2~N인 정사각형 탐색
        for(int k=1;k<arr.length;k++){
            for(int row=k;row<arr.length && k<arr[0].length;row++){
                dp[row][k] = makeSquare(row,k,arr,dp);
                max = Math.max(dp[row][k],max);
            }
            for(int col=k+1;col<arr[0].length;col++){
                dp[k][col] = makeSquare(k,col,arr,dp);
                max = Math.max(dp[k][col],max);
            }
        }

        System.out.println(max*max);
    }
    public static int makeSquare(int row, int col,int[][]arr,int[][]dp){
        int[] dx = {-1,-1,0};
        int[] dy = {-1,0,-1};

        if(arr[row][col] == 0){
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for(int i=0;i<dx.length;i++){
            min = Math.min(min,dp[row+dx[i]][col+dy[i]]);
        }

        return min + 1;
    }
}

