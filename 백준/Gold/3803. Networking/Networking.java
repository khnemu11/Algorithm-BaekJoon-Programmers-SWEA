import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String[] row = br.readLine().split(" ");

            if(row.length == 1){
                break;
            }

            int P = Integer.parseInt(row[0]);
            int R = Integer.parseInt(row[1]);

            parent = new int[P+1];

            for(int i=0;i<=P;i++){
                parent[i] = i;
            }

            PriorityQueue<Road> pq = new PriorityQueue<>();

            for(int i=0;i<R;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

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

                total += curr.cost;
                union(curr.start,curr.end);
            }

            System.out.println(total);
            br.readLine();
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