import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        parent = new int[N+1];

        for(int i=0;i<=N;i++){
            parent[i] = i;
        }

        PriorityQueue<Road> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Road(0,i,cost));
        }

        HashSet<String> banSet = new HashSet<>();

        for(int m=0;m<M;m++){
            st = new StringTokenizer(br.readLine());
            String i = st.nextToken();
            String j = st.nextToken();

            banSet.add(i+"|"+j);
        }

        for(int i=1;i<=N;i++){
            int right = i + 1 > N ? 1 : i + 1;
            if(!banSet.contains(right+"|"+i) && !banSet.contains(i+"|"+right)){
                pq.add(new Road(i,right,0));
            }
        }

        long total = 0;

        if(M > 1){
            while (!pq.isEmpty()) {
                Road curr = pq.poll();

                if(getParent(curr.start) == getParent(curr.end)){
                    continue;
                }

                total += curr.cost;
                union(curr.start,curr.end);
            }

        }

        System.out.println(total <= K ? "YES" : "NO");
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

    @Override
    public String toString() {
        return "Road{" +
                "start=" + start +
                ", end=" + end +
                ", cost=" + cost +
                '}';
    }
}