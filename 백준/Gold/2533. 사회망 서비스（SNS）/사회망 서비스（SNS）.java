import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final static int TRUE = 1;
    final static int FALSE = 0;
    static boolean[] visited;
    static List<Integer> graph[];
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        dp = new int[N+1][2];

        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        findCount(1,TRUE);

        System.out.println(Math.min(dp[1][TRUE],dp[1][FALSE]));
    }

    public static int findCount(int curr,int valid){
        if(visited[curr]){
            return dp[curr][valid];
        }
        visited[curr] = true;

        dp[curr][TRUE] = 1;
        dp[curr][FALSE] = 0;

        for(int next : graph[curr]){
            if(visited[next]){
                continue;
            }
            int nextTrue = findCount(next, TRUE);
            int nextFalse = findCount(next, FALSE);

            dp[curr][FALSE] += nextTrue;
            dp[curr][TRUE] += Math.min(nextTrue,nextFalse);
        }

        return dp[curr][valid];
    }
}