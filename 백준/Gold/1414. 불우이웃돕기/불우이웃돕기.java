import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        parent = new int[N];

        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }

        PriorityQueue<Road> pq = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            String row = br.readLine();

            for(int j=0;j<row.length();j++){
                if(row.charAt(j) == '0'){
                    continue;
                }

                int cost = 0;

                if(row.charAt(j) >= 'a'){
                    cost = row.charAt(j) -'a' + 1;
                }else{
                    cost = row.charAt(j) -'A' + 27;
                }

                Road road = new Road(i,j,cost);
                pq.add(road);
            }
        }

        int total = 0;

        while(!pq.isEmpty()){
            Road road = pq.poll();

            if(getParent(road.start) == getParent(road.end)){
                total += road.cost;;
                continue;
            }

            union(road.start,road.end);
        }

        boolean connect = true;

        for(int i=0;i<parent.length-1;i++){
            if(getParent(i) != getParent(i+1)){
                connect = false;
            }
        }

        System.out.println(connect ?  total : -1);
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