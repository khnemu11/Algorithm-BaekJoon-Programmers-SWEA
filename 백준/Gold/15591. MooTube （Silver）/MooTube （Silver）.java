import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Video> graph [];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        long INF = Long.MAX_VALUE;

        graph= new ArrayList[N+1];

        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            graph[p].add(new Video(q,r));
            graph[q].add(new Video(p,r));
        }



        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[N+1];
            Queue<Integer> q= new LinkedList<>();
            int count = 0;

            visited[v] = true;
            q.add(v);

            while(!q.isEmpty()){
                int curr = q.poll();

                for(Video next : graph[curr]){
                    if(!visited[next.seq] && next.usado >= k){
                        count++;
                        visited[next.seq] = true;
                        q.add(next.seq);
                    }
                }
            }

            System.out.println(count);
        }
    }
}
class Video{
    int seq;
    long usado;

    public Video(int seq, long usado){
        this.seq = seq;
        this.usado =usado;
    }
}