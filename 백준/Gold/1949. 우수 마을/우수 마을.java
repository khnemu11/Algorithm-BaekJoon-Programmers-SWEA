import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/*
*
* 풀이 시간 : 46분
* 
* */

public class Main {
    static int USED = 0;
    static int UNUSED = 1;
    static List<Integer> graph[];
    static int[][] dp;
    static int[] people;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        people = new int[N+1];
        graph = new ArrayList[N+1];
        dp = new int[2][N+1];
        visited = new boolean[N+1];

        for(int i=1;i<=N;i++){
            graph[i] = new ArrayList<>();
            people[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }
        
        System.out.println(Math.max(getMaxPeople(USED,1), getMaxPeople(UNUSED,1)));
    }

    public static int getMaxPeople(int status,int n){
        if(visited[n]){
            return dp[status][n];
        }

        visited[n] = true;
        dp[USED][n] = people[n];

        for(int next : graph[n]){
            if(visited[next]){
                continue;
            }
            int unUsedSum = getMaxPeople(UNUSED,next);;
            int usedSum = getMaxPeople(USED,next);;

            dp[USED][n] += unUsedSum;
            dp[UNUSED][n] += Math.max(unUsedSum,usedSum);
        }

        return dp[status][n];
    }
}