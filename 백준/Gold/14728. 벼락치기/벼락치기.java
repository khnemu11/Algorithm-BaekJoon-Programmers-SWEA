import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] maxScore = new int[N+1][T+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            for(int t=1;t<K && t <= T;t++){
                maxScore[i][t] = maxScore[i-1][t];
            }

            for(int t=K;t<=T;t++){
                maxScore[i][t] = Math.max(maxScore[i-1][t],maxScore[i-1][t-K] + S);
            }
        }

        System.out.println(maxScore[N][T]);
    }
}