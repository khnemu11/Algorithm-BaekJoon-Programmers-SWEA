import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        long maxTime = 0L;
        long totalCost = 0L;

        PriorityQueue<Road> pq = new PriorityQueue<>();

        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            pq.add(new Road(from,to,cost,time));
        }

        while(!pq.isEmpty()){
            Road curr = pq.poll();

            int pa = getParent(curr.start);
            int pb = getParent(curr.end);

            if(pa == pb){
                continue;
            }

            union(pa,pb);
            totalCost += curr.cost;
            maxTime = Math.max(maxTime, curr.time);
        }

        Set<Integer> parentSet = new HashSet<>();

        for(int i=1;i<=n;i++){
            parentSet.add(getParent(i));
        }

        if(parentSet.size() == 1){
            System.out.println(maxTime+" "+totalCost);
        }else{
            System.out.println(-1);
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

class Road implements Comparable<Road>{
    int start;
    int end;
    int time;

    int cost;


    public Road(int start, int end, int cost,int time){
        this.start= start;
        this.end = end;
        this.cost = cost;
        this.time = time;
    }

    @Override
    public int compareTo(Road o) {
        return this.cost == o.cost ? this.time - o.time : this.cost - o.cost;
    }
}