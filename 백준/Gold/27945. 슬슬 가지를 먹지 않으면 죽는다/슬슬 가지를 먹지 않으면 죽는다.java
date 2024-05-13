import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Restaurant> restaurantList = new ArrayList<>();
        PriorityQueue<Restaurant> pq = new PriorityQueue<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            Restaurant restaurant = new Restaurant(start,end,day);

            restaurantList.add(restaurant);
            pq.add(restaurant);
        }

        parent = new int[N+1];

        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }

        Set<Integer> graph[] = new HashSet[N+1];

        for(int i=0;i<graph.length;i++){
            graph[i] = new HashSet<>();
        }

        while(!pq.isEmpty()){
            Restaurant curr = pq.poll();

            int pa = getParent(curr.start);
            int pb = getParent(curr.end);

            if(pa != pb){
                graph[curr.start].add(curr.end);
                graph[curr.end].add(curr.start);

                union(pa,pb);
            }
        }

        int day = 1;

        Collections.sort(restaurantList);

        for(Restaurant restaurant : restaurantList){
            if(restaurant.day != day){
                break;
            }
            if(!graph[restaurant.start].contains(restaurant.end)){
               break;
            }
            day++;
        }

        System.out.println(day);
    }

    public static int getParent(int curr){
        if(parent[curr] == curr){
            return curr;
        }

        parent[curr] = getParent(parent[curr]);

        return parent[curr];
    }

    public static void union(int a, int b){
        int pa = getParent(a);
        int pb = getParent(b);

        if(pa < pb){
            parent[a] = b;
        }else{
            parent[b] = a;
        }
    }
}

class Restaurant implements Comparable<Restaurant>{
    int start;
    int end;
    int day;

    public Restaurant(int start, int end, int day){
        this.start = start;
        this.end = end;
        this.day = day;
    }

    @Override
    public int compareTo(Restaurant o) {
        return this.day - o.day;
    }
}