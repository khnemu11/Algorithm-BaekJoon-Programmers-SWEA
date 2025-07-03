import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean isFinish = false;

        while(!isFinish){
            int N = Integer.parseInt(br.readLine());

            if (N == 0){
                isFinish = true;
                continue;
            }

            parent = new int[N];

            PriorityQueue<Road> pq = new PriorityQueue<>();

            for(int i=0; i<parent.length;i++){
                parent[i] = i;
            }

            for(int i=0;i<N-1;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                String village = st.nextToken();

                int cnt = Integer.parseInt(st.nextToken());

                for(int j=0; j<cnt;j++){
                    String end = st.nextToken();
                    int distance = Integer.parseInt(st.nextToken());

                    Road road = new Road(village.charAt(0) - 'A', end.charAt(0) - 'A', distance);
                    pq.add(road);
                }
            }

            int sum = 0;

            while(!pq.isEmpty()){
                Road curr = pq.poll();

                if(getParent(curr.start) == getParent(curr.end)){
                    continue;
                }

                sum += curr.distance;

                union(getParent(curr.start), getParent(curr.end));
            }

            System.out.println(sum);
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