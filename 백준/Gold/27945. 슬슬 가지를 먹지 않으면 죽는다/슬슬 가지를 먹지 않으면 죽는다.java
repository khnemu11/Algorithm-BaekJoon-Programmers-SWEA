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

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            Restaurant restaurant = new Restaurant(start,end,day);

            restaurantList.add(restaurant);
        }
        
        //노점이 빨리 여는 순서부터 정렬
        Collections.sort(restaurantList);

        parent = new int[N+1];

        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }

        Set<Integer> graph[] = new HashSet[N+1];

        for(int i=0;i<graph.length;i++){
            graph[i] = new HashSet<>();
        }

        //가능한 한번도 끊기지 않고 연속적으로 노점을 가야하므로 빨리 여는 노점부터 선택
        for(Restaurant restaurant : restaurantList){
            int pa = getParent(restaurant.start);
            int pb = getParent(restaurant.end);

            if(pa != pb){
                graph[restaurant.start].add(restaurant.end);
                graph[restaurant.end].add(restaurant.start);

                union(pa,pb);
            }
        }

        int day = 1;
        
        //n일차 노점이 있는 경로가 있는지 확인 찾기
        for(Restaurant restaurant : restaurantList){
            //현재 날짜의 노점이 아니라면 키위는 쓰러진다.
            if(restaurant.day != day){
                break;
            }
            //n일차 노점을 갈 수 없으면 키위는 쓰러진다.
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