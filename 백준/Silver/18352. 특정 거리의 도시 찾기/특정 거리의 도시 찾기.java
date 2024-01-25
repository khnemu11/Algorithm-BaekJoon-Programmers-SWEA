import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int M = input[1];
        int K = input[2];
        int X = input[3];

        List<City> graph[] = new ArrayList[N+1];

        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[input[0]].add(new City(input[1],1));
        }

        int[] distance = new int[N+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[X] = 0;

        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(X,distance[X]));

        while(!pq.isEmpty()){
            City curr = pq.poll();

            if(curr.distance>distance[curr.seq]){
                continue;
            }

            for(City next : graph[curr.seq]){
                if(distance[next.seq] > curr.distance + next.distance){
                    distance[next.seq] = curr.distance + next.distance;
                    pq.add(new City(next.seq,distance[next.seq]));
                }
            }
        }

        List<Integer> targetCity = new ArrayList<>();

        for(int i=1;i<distance.length;i++){
            if(i == X){
                continue;
            }
            if(distance[i] == K){
                targetCity.add(i);
            }
        }

        if(targetCity.isEmpty()){
            System.out.println(-1);
        }else{
            for(Integer city : targetCity){
                System.out.println(city);
            }
        }
    }
}

class City implements Comparable<City>{
    int seq;
    int distance;

    public City(int seq, int distance){
        this.seq = seq;
        this.distance = distance;
    }


    @Override
    public int compareTo(City o) {
        return this.distance - o.distance;
    }
}