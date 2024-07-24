import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int WIDTH = 0;
    private static int HEIGHT = 1;
    private static int DIAG = 2;

    private static int WALL = 1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][]dp = new long[3][N+1][N+1];
        dp[WIDTH][1][2] =1;

        for(int i=3;i<=N;i++){
            if(map[1][i] == WALL){
                break;
            }
            dp[WIDTH][1][i] = 1;
        }

        for(int i=2;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(map[i][j] == WALL){
                    continue;
                }

                dp[WIDTH][i][j] = dp[WIDTH][i][j-1] + dp[DIAG][i][j-1];
                dp[HEIGHT][i][j] = dp[HEIGHT][i-1][j] + dp[DIAG][i-1][j];

                if(map[i-1][j] != WALL && map[i][j-1] != WALL) {
                    dp[DIAG][i][j] = dp[DIAG][i-1][j-1] + dp[WIDTH][i-1][j-1] + dp[HEIGHT][i-1][j-1];
                }
            }
        }


        long count = dp[WIDTH][N][N] + dp[DIAG][N][N] + dp[HEIGHT][N][N];

        System.out.println(count);
    }
}