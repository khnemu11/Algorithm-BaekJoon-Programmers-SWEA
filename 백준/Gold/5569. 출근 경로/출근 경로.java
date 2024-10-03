import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int RIGHT = 0;
    public static int DOWN = 1;

    public static int NOT_ROTATE = 0;
    public static int ROTATE = 1;

    public static int MOD = 100_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][][]dp = new int[h+1][w+1][2][2];

        for(int i=1;i<=h;i++){
            dp[i][1][1][0] = 1;
        }
        for(int i=1;i<=w;i++){
            dp[1][i][0][0] = 1;
        }

        for(int i=2;i<=h;i++) {
            for(int j=2;j<=w;j++) {
                dp[i][j][RIGHT][NOT_ROTATE] = (dp[i][j-1][RIGHT][NOT_ROTATE] + dp[i][j-1][RIGHT][ROTATE]) % MOD;
                dp[i][j][RIGHT][ROTATE] = dp[i][j-1][DOWN][NOT_ROTATE]  % MOD;
                dp[i][j][DOWN][NOT_ROTATE] = (dp[i-1][j][DOWN][NOT_ROTATE] + dp[i-1][j][DOWN][ROTATE]) % MOD;
                dp[i][j][DOWN][ROTATE] = dp[i-1][j][RIGHT][NOT_ROTATE] % MOD;
            }
        }

        int result = (dp[h][w][RIGHT][NOT_ROTATE] + dp[h][w][RIGHT][ROTATE] +dp[h][w][DOWN][NOT_ROTATE] +dp[h][w][DOWN][ROTATE]) % MOD;

        System.out.println(result);
    }

    public static void printArr(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
