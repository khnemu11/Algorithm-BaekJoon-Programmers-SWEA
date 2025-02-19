import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[M+1][N+1];

        List<Trip> graph[] = new ArrayList[N+1];

        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(start > end){
                continue;
            }

            graph[start].add(new Trip(end,cost,0));
        }

        PriorityQueue<Trip> pq = new PriorityQueue<>((a,b)-> a.cnt - b.cnt);

        pq.add(new Trip(1,0,1));

        while(!pq.isEmpty()){
            Trip curr = pq.poll();

            if(curr.cnt == M){
                continue;
            }

            if(dp[curr.cnt][curr.city] > curr.cost){
                continue;
            }

            for(Trip next : graph[curr.city]){
                if(dp[curr.cnt+1][next.city] < curr.cost + next.cost){
                    dp[curr.cnt+1][next.city] = curr.cost + next.cost;
                    pq.add(new Trip(next.city,curr.cost + next.cost,curr.cnt+1));
                }
            }
        }

        int max=0;

        for(int i=0;i<dp.length;i++){
            max = Math.max(max,dp[i][N]);
        }

        System.out.println(max);
    }
}

class Trip{
    int city;
    int cost;
    int cnt;
    public Trip(int city, int cost, int cnt){
        this.city = city;
        this.cost = cost;
        this.cnt = cnt;
    }
}
