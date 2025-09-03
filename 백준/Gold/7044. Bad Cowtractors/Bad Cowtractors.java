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

        PriorityQueue<Road> maxPq = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o2.cost - o1.cost;
            }
        });


        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            maxPq.add(new Road(from,to,cost));
        }


        int max = 0;
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
            max += curr.cost;
        }

        boolean isAllBarn = true;

        for(int i=1;i<N;i++){
            if(getParent(i) != getParent(i+1)){
                isAllBarn = false;
                break;
            }
        }

        System.out.println(isAllBarn ? max : -1);
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


    public Road(int start, int end, int isUpStair){
        this.start= start;
        this.end = end;
        this.cost = isUpStair;
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