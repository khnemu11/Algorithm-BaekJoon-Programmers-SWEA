import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        List<House> graph[] = new ArrayList[N];

        for(int i=0;i<N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph[start].add(new House(end,distance));
            graph[end].add(new House(start,distance));
        }

        int[] distance = new int[N];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[Y] = 0;

        PriorityQueue<House> pq = new PriorityQueue<>();
        pq.add(new House(Y,0));

        while(!pq.isEmpty()){
            House curr = pq.poll();

            if(curr.distance > distance[curr.n]){
                continue;
            }

            for(House next : graph[curr.n]){
                if(distance[next.n] > distance[curr.n] + next.distance){
                    distance[next.n] = distance[curr.n] + next.distance;
                    pq.add(new House(next.n,distance[next.n]));
                }
            }
        }

        int dayMove= 0;
        int day = 1;
        Arrays.sort(distance);

        for(int i=0;i<distance.length;i++){
            if(distance[i] * 2 > X){
                day = -1;
                break;
            }
            else if(dayMove + distance[i]*2 > X){
                day++;
                dayMove = distance[i]*2;
            }
            else{
                dayMove += distance[i]*2;
            }
        }

        System.out.println(day);
    }
}
class House implements Comparable<House>{
    int n;
    int distance;

    public House(int n,int distance){
        this.n = n;
        this.distance = distance;
    }

    @Override
    public int compareTo(House o) {
        return this.distance - o.distance;
    }
}