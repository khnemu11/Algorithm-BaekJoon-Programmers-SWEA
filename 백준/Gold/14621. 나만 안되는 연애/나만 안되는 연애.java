import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];

        for(int i=1;i<parent.length;i++){
            parent[i] = i;
        }

        boolean[] isMan = new boolean[N+1];
        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            String gender = st.nextToken();

            isMan[i] = gender.equals("M");
        }

        PriorityQueue<Road> pq = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o1.cost - o2.cost;
            }
        });

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(isMan[start] != isMan[end]){
                pq.add(new Road(start, end, cost));
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

        boolean canMakeRoad = true;

        for(int i=1;i<parent.length-1;i++){
            if(getParent(i) != getParent(i+1)){
                canMakeRoad = false;
                break;
            }
        }

        System.out.println(canMakeRoad ? min : -1);
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