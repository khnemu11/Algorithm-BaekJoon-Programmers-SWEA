import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int [][] map;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for(int i=0;i<map.length;i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp[0][0] = map[0][0];

        for(int j=1;j<dp[0].length;j++){
            dp[0][j] = map[0][j] + dp[0][j-1];
        }

        for(int i=1;i<map.length;i++){
            int[] leftDp = new int[M];
            leftDp[0] = dp[i-1][0] + map[i][0];

            for(int j=1;j<map[i].length;j++) {
                leftDp[j] = Math.max(leftDp[j-1],dp[i-1][j]) + map[i][j];
            }

            int[] rightDp = new int[M];
            rightDp[map[i].length-1] = dp[i-1][map[i].length-1] + map[i][map[i].length-1];

            for(int j=map[i].length-2;j>=0;j--) {
                rightDp[j] = Math.max(rightDp[j+1],dp[i-1][j]) + map[i][j];
            }

            for(int j=0;j<dp[i].length;j++){
                dp[i][j] = Math.max(leftDp[j],rightDp[j]);
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}