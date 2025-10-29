import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        PriorityQueue<Road> pq = new PriorityQueue<>();
        List<Road> graph[] = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            graph[h1].add(new Road(h1, h2, k));
            graph[h2].add(new Road(h2, h1, k));
        }

        pq.addAll(graph[start]);

        int min = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Road curr = pq.poll();

            if (visited[curr.end]) {
                continue;
            }

            visited[curr.start] = true;
            visited[curr.end] = true;

            min = Math.min(min, curr.cost);

            if (curr.end == goal) {
                break;
            }

            for (Road next : graph[curr.end]) {
                if (visited[next.end]) {
                    continue;
                }

                pq.add(next);
            }
        }

        if(min == Integer.MAX_VALUE){
            min = 0;
        }

        System.out.println(min);
    }
}
class Road implements Comparable<Road>{
    int start;
    int end;
    int cost;

    public Road(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Road o) {
        return o.cost - this.cost;
    }
}