import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        int[] answers = new int[K];

        PriorityQueue<Road> currPQ = new PriorityQueue<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Road road = new Road(start,end,i+1);
            currPQ.add(road);
        }

        for(int k=0;k<K;k++){
            for(int i=0;i<parent.length;i++){
                parent[i] = i;
            }

            int sum = 0;
            PriorityQueue<Road> nextPQ = new PriorityQueue<>();

            while(!currPQ.isEmpty()){
                Road curr = currPQ.poll();
                nextPQ.add(curr);

                if(getParent(curr.start) == getParent(curr.end)){
                    continue;
                }

                union(curr.start,curr.end);
                sum += curr.cost;
            }

            boolean isMST=true;

            for(int i=1;i<parent.length-1;i++){
                if(getParent(parent[i]) != getParent(parent[i+1])){
                    isMST=false;
                    break;
                }
            }

            if(!isMST){
                break;
            }

            answers[k] = sum;
            nextPQ.poll();
            currPQ = nextPQ;
        }

        System.out.println(Arrays.stream(answers).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int getParent(int i){
        if(parent[i] == i){
            return parent[i];
        }
        return parent[i] = getParent(parent[i]);
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
    int cost;

    public Road(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Road o) {
        return this.cost - o.cost;
    }
}