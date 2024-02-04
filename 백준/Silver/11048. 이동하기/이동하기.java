import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int[]input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][]maze = new int[input[0]][input[1]];
        int[][]dp = new int[input[0]+1][input[1]+1];

        for(int i=0;i<maze.length;i++){
            maze[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[i].length;j++){
                dp[i][j] = Math.max(dp[i-1][j-1],dp[i-1][j]);
                dp[i][j] = Math.max(dp[i][j],dp[i][j-1]);
                dp[i][j]+=maze[i-1][j-1];
            }
        }

        System.out.println(dp[dp.length-1][dp[0].length-1]);
    }
}