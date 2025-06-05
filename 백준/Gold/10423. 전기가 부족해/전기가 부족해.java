import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] parent;
    public static boolean[] isPowerStation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Wire> graph[] = new ArrayList[N+1];
        isPowerStation = new boolean[N+1];

        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        parent = new int[N+1];
        int[] powerStations = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int powerStation : powerStations) {
            isPowerStation[powerStation] = true;
        }

        PriorityQueue<Wire> pq = new PriorityQueue<>();

        for(int i=0;i<=N;i++){
            parent[i] = i;
        }

        for(int i=1;i<=M;i++){
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Wire wire = new Wire(row[0], row[1], row[2]);

            graph[wire.start].add(new Wire(row[0],row[1],row[2]));
            graph[wire.end].add(new Wire(row[1],row[0],row[2]));

            if(isPowerStation[wire.start] || isPowerStation[wire.end]){
                pq.add(wire);
            }
        }

        long sum = 0;

        while(!pq.isEmpty()){
            Wire curr = pq.poll();

            int pa = getParent(curr.start);
            int pb = getParent(curr.end);

            if(pa == pb){
                continue;
            }

            if(isPowerStation[pa] && isPowerStation[pb]){
                continue;
            }
            sum += curr.distance;
            setParent(pa, pb);

            for(Wire wire : graph[curr.end]){
                if(getParent(wire.start) != getParent(wire.end)){
                    pq.add(wire);
                }
            }
            for(Wire wire : graph[curr.start]){
                if(getParent(wire.start) != getParent(wire.end)){
                    pq.add(wire);
                }
            }
        }

        System.out.println(sum);
    }

    public static int getParent(int i){
        if(parent[i] == i){
            return parent[i];
        }
        return parent[i] = getParent(parent[i]);
    }

    public static void setParent(int pa, int pb){
        if(isPowerStation[pa]){
            parent[pb] = pa;
        }else{
            parent[pa] = pb;
        }
    }
}

class Wire implements Comparable<Wire>{
    int start;
    int end;
    int distance;

    public Wire(int start, int end, int distance){
        this.start= start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Wire o) {
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