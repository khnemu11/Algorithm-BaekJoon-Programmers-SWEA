import java.io.*;
import java.util.Arrays;

public class Main {
    final static int EMPTY = 0;
    final static int LEFT = 1;
    final static int RIGHT = 2;
    final static int MOD = 9901;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int N = Integer.parseInt(br.readLine());
        int[][]cage = new int[N+1][3];

        Arrays.fill(cage[1],1);

        for(int i=2;i<=N;i++){
            cage[i][LEFT] = (cage[i-1][RIGHT] + cage[i-1][EMPTY])%MOD;
            cage[i][RIGHT] = (cage[i-1][LEFT] + cage[i-1][EMPTY])%MOD;
            cage[i][EMPTY] = (cage[i-1][LEFT] + cage[i-1][RIGHT])%MOD;
            cage[i][EMPTY] = (cage[i][EMPTY] + cage[i-1][EMPTY])%MOD;
        }

        int sum = (cage[N][LEFT] + cage[N][RIGHT])%MOD;
        sum = (sum + cage[N][EMPTY])%MOD;
        
        System.out.println(sum);
    }
}