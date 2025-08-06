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

        PriorityQueue<Road> minPq = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o2.isUpStair - o1.isUpStair;
            }
        });

        PriorityQueue<Road> maxPq = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o1.isUpStair - o2.isUpStair;
            }
        });



        for(int i=0;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int isUpStair = Integer.parseInt(st.nextToken());

            minPq.add(new Road(from,to,isUpStair));
            maxPq.add(new Road(from,to,isUpStair));
        }

        int minK = 0;
        parent = new int[N+1];

        for(int i=0;i<=N;i++){
            parent[i] = i;
        }

        while(!minPq.isEmpty()){
            Road curr = minPq.poll();

            int pa = getParent(curr.start);
            int pb = getParent(curr.end);

            if(pa == pb){
                continue;
            }

            union(pa,pb);
            minK += curr.isUpStair == 1 ? 0 : 1;
        }

        int maxK = 0;
        parent = new int[N+1];

        for(int i=0;i<=N;i++){
            parent[i] = i;
        }

        while(!maxPq.isEmpty()){
            Road curr = maxPq.poll();

            int pa = getParent(curr.start);
            int pb = getParent(curr.end);

            if(pa == pb){
                continue;
            }

            union(pa,pb);
            maxK += curr.isUpStair == 1 ? 0 : 1;
        }

        minK = minK * minK;
        maxK = maxK * maxK;

        System.out.println(maxK - minK);
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
    int isUpStair;


    public Road(int start, int end, int isUpStair){
        this.start= start;
        this.end = end;
        this.isUpStair = isUpStair;
    }

    @Override
    public String toString() {
        return "Road{" +
                "start=" + start +
                ", end=" + end +
                ", isUpStair=" + isUpStair +
                '}';
    }
}