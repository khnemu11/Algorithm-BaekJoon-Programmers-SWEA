import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            parent = new int[R*C];

            for(int i=0;i<parent.length;i++){
                parent[i] = i;
            }

            PriorityQueue<Road> pq = new PriorityQueue<>(new Comparator<Road>() {
                @Override
                public int compare(Road o1, Road o2) {
                    return o1.cost - o2.cost;
                }
            });


            for(int r=0;r<R;r++){
                st = new StringTokenizer(br.readLine());

                for(int c=0;c<C-1;c++){
                    int cost = Integer.parseInt(st.nextToken());
                    pq.add(new Road(r * C + c, r * C + c+1, cost));
                }
            }

            for(int r=0;r<R-1;r++){
                st = new StringTokenizer(br.readLine());

                for(int c=0;c<C;c++){
                    int cost = Integer.parseInt(st.nextToken());
                    pq.add(new Road(r * C + c, (r +1)*C + c, cost));
                }
            }

            long min = 0;

            while(!pq.isEmpty()){
                Road curr = pq.poll();

                int pa = getParent(curr.start);
                int pb = getParent(curr.end);

                if(pa == pb){
                    continue;
                }

                union(pa,pb);
                min = min + curr.cost;
            }

            System.out.println(min);
        }
    }

    public static int getParent(int i){
        if(parent[i] == i){
            return parent[i];
        }
        return parent[i] = getParent(parent[i]);
    }

    public static void union(int pa, int pb){
        if(pa < pb){
            parent[pb] = pa;
        }else{
            parent[pa] = pb;
        }
    }
}
class Road {
    int start;
    int end;
    int cost;


    public Road(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
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