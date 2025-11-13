import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];

        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }

        PriorityQueue<Road> pq = new PriorityQueue<>();
        List<Road> selectRoad = new ArrayList<>();

        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=1;j<i;j++){
                int cost = Integer.parseInt(st.nextToken());
                pq.add(new Road(i,j,cost));
            }
        }

        int C = 0;
        int M = 0;

        while (!pq.isEmpty()) {
            Road curr = pq.poll();

            if(curr.cost < 0 ){
                C += curr.cost * -1;
                union(curr.start, curr.end);
            }else if(getParent(curr.start) != getParent(curr.end)){
                union(curr.start, curr.end);
                selectRoad.add(curr);
                C += curr.cost;;
                M++;
            }
        }

        System.out.printf("%d %d\n",C,M);
        for(int i=0;i<M;i++){
            System.out.printf("%d %d\n",selectRoad.get(i).start,selectRoad.get(i).end);
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