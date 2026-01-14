import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Road> pq = new PriorityQueue<>();

        for(int i=1;i<=N;i++){
            int cost = Integer.parseInt(br.readLine());
            pq.add(new Road(0,i,cost));
        }

        parent = new int[N+1];

        for(int i=0;i<=N;i++){
            parent[i] = i;
        }

        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=1;j<=N;j++){
                int cost = Integer.parseInt(st.nextToken());
                pq.add(new Road(i,j,cost));
            }
        }

        long sum = 0;

        while(!pq.isEmpty()) {
            Road curr = pq.poll();

            if (getParent(curr.start) == getParent(curr.end)) {
                continue;
            }

            union(curr.start, curr.end);
            sum += curr.cost;
        }

        System.out.println(sum);
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
        return this.cost - o.cost ;
    }

    @Override
    public String toString() {
        return "Road{" +
                ", start=" + start +
                ", end=" + end +
                ", cost=" + cost +
                '}';
    }
}