import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Road> pq = new PriorityQueue<>();

        parent = new int[N+1];

        for(int i=0;i<=N;i++){
            parent[i] = i;
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Road(start,end,num,cost));
        }

        PriorityQueue<Integer> numQ = new PriorityQueue<>();
        long sum = 0 ;

        while(!pq.isEmpty()) {
            Road curr = pq.poll();

            if (getParent(curr.start) == getParent(curr.end)) {
                continue;
            }

            union(curr.start, curr.end);
            numQ.add(curr.num);
            sum +=curr.cost;
        }

        boolean valid = true;

        for(int i=1; i<N;i++){
            if(getParent(i) != getParent(i+1)){
                valid =false;
            }
        }

        StringBuilder numStr = new StringBuilder();

        if(valid){
            while(!numQ.isEmpty()){
                numStr.append(numQ.poll());
            }
            System.out.println(numStr.toString() +" " + sum);
        }else{
            System.out.println(-1);
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
    int num;
    int cost;

    public Road(int start, int end, int num, int cost) {
        this.start = start;
        this.end = end;
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Road o) {
        if(this.num == o.num){
            return this.cost - o.cost;
        }
        return this.num - o.num ;
    }

    @Override
    public String toString() {
        return "Road{" +
                ", start=" + start +
                ", end=" + end +
                ", cost=" + cost +
                '}';
    }
}