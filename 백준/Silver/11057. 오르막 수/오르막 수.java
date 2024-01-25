import java.io.*;
import java.util.*;

public class Main {
    final static int MOD = 10_007;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int N = Integer.parseInt(br.readLine());

        int[][] upStairs = new int[N+1][10];
        Arrays.fill(upStairs[1],1);

        for(int i=2;i<=N;i++){
            for(int j=0;j<10;j++){
                for(int k=0;k<=j;k++){
                    upStairs[i][j] = (upStairs[i][j] + upStairs[i-1][k]) % MOD;
                }
            }
        }

        int sum = 0;

        for(int i=0;i<10;i++){
            sum = (sum + upStairs[N][i]) % MOD;
        }

        System.out.println(sum);
    }
}

