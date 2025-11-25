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
        int C = Integer.parseInt(st.nextToken());

        parent = new int[N];

        for(int i=0;i<N;i++){
            parent[i] = i;
        }

        List<Coordinate> list = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Coordinate(i,x,y));
        }

        PriorityQueue<Road> pq = new PriorityQueue<>();

        for(int i=0;i<list.size();i++){
            for(int j=i+1;j<list.size();j++){
                long cost = (long) (Math.pow(list.get(i).x - list.get(j).x,2) + Math.pow(list.get(i).y - list.get(j).y,2));

                pq.add(new Road(i,j,cost));
            }
        }

        long total = 0;

        while (!pq.isEmpty()) {
            Road curr = pq.poll();

            if(curr.cost < C){
                continue;
            }
            if(getParent(curr.start) == getParent(curr.end)){
                continue;
            }

            total += curr.cost;
            union(curr.start,curr.end);
        }

        boolean valid = true;

        for(int i=0;i<parent.length-1;i++){
            if(getParent(i) != getParent(i+1)){
                valid = false;
                break;
            }
        }

        System.out.println(valid ? total : -1);
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
class Coordinate{
    int seq;
    int x;
    int y;

    public Coordinate(int seq, int x, int y){
        this.seq = seq;
        this.x = x;
        this.y = y;
    }
}
class Road implements Comparable<Road>{
    int start;
    int end;
    long cost;

    public Road(int start, int end, long cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Road o) {
        return Long.compare(this.cost, o.cost);
    }
}