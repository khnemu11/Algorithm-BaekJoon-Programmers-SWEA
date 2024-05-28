import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int INF = 10000000;

        int[] woks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Set<Integer> wokComb = new TreeSet<>();

        for(int i=0;i<woks.length;i++){
            wokComb.add(woks[i]);

            for(int j=i+1;j<woks.length;j++){
                wokComb.add(woks[i]+woks[j]);
            }
        }

        int[][] dp = new int[wokComb.size()+1][N+1];
        Arrays.fill(dp[0],INF);
        dp[0][0] = 0;

        int i = 0;
        for(int wok : wokComb){
            for(int k=1;k <wok && k <=N;k++){
                dp[i+1][k] = dp[i][k];
            }
            for(int k=wok;k<=N;k++){
                dp[i+1][k] = Math.min(dp[i][k], dp[i+1][k-wok]+1);
                dp[i+1][k] = Math.min(dp[i+1][k], dp[i][k-wok]+1);
            }
            i++;
        }

        if(dp[wokComb.size()][N] == INF){
            System.out.println(-1);
        }else{
            System.out.println(dp[wokComb.size()][N]);
        }
    }
}