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

        parent = new int[N+1];

        for(int i=1;i<=N;i++){
            parent[i] = i;
        }

        PriorityQueue<Love> pq = new PriorityQueue<>();
        int sum = 0;

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());
            int isCouple = Integer.parseInt(st.nextToken());

            if(isCouple == 1){
                union(start,end);
            }else{
                pq.add(new Love(start,end,power));
            }
        }

        while(!pq.isEmpty()){
            Love curr = pq.poll();

            if(getParent(curr.start) == getParent(curr.end)){
                sum += curr.power;
                continue;
            }

            union(curr.start,curr.end);
        }

        System.out.println(sum);
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
class Love implements Comparable<Love>{
    int start;
    int end;
    int power;

    public Love(int start, int end, int power) {
        this.start = start;
        this.end = end;
        this.power = power;
    }

    @Override
    public int compareTo(Love o) {
        return o.power - this.power ;
    }
}