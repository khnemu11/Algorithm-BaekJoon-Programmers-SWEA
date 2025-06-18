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
        PriorityQueue<Road> pq = new PriorityQueue<>();

        for(int i=1;i<=N;i++){
            parent[i] = i;
        }

        long total = 0;

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            Road road = new Road(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            total += road.distance;
            pq.add(road);
        }

        long result = 0;

        while(!pq.isEmpty()){
            Road curr = pq.poll();

            int pa = getParent(curr.start);
            int pb = getParent(curr.end);

            if(pa == pb){
                continue;
            }

            union(pa,pb);
            result += curr.distance;
        }

        if(!isAllConnected()){
            System.out.println(-1);
        }else{
            System.out.println(total - result);
        }
    }

    public static boolean isAllConnected(){
        Set<Integer> parentSet = new HashSet<>();

        for(int i=1; i < parent.length; i++){
            getParent(i);
            parentSet.add(parent[i]);
        }

        return parentSet.size() == 1;
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
    int distance;

    public Road(int start, int end, int distance){
        this.start= start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Road o) {
        return this.distance - o.distance;
    }

    @Override
    public String toString() {
        return "Wire{" +
                "start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                '}';
    }
}