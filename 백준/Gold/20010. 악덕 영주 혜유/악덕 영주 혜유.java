import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static long max = 0L;
    static List<Road> graph[];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        graph = new ArrayList[N];

        for(int i=0;i<N;i++){
            parent[i] = i;
            graph[i] = new ArrayList<>();
        }

        PriorityQueue<Road> pq = new PriorityQueue<>();

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Road(start,end,cost));
        }

        long total = 0;

        while (!pq.isEmpty()) {
            Road curr = pq.poll();

            if(getParent(curr.start) == getParent(curr.end)){
                continue;
            }

            graph[curr.start].add(new Road(curr.start,curr.end,curr.cost));
            graph[curr.end].add(new Road(curr.end,curr.start,curr.cost));

            total += curr.cost;
            union(curr.start,curr.end);
        }

        System.out.println(total);

        for(int i=0;i<N;i++){
            visited = new boolean[N];
            findMaxLength(i,0L);
        }

        System.out.println(max);
    }

    public static void findMaxLength(int start,long cost){
        visited[start] = true;

        max = Math.max(max, cost);

        for(Road next : graph[start]){
            if(!visited[next.end]){
                visited[next.end] = true;
                findMaxLength(next.end, next.cost + cost);
            }
        }
    }

    public static int getParent(int a){
        if(parent[a] == a){
            return a;
        }

        return parent[a] = getParent(parent[a]);
    }

    public static void union(int a, int b){
        int pa = getParent(a);
        int pb = getParent(b);

        if(pa < pb){
            parent[pb] = pa;
        }else{
            parent[pa] = pb;
        }
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
        return this.cost - o.cost;
    }

    @Override
    public String toString() {
        return "Road{" +
                "start=" + start +
                ", end=" + end +
                ", cost=" + cost +
                '}';
    }
}