import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        City[] cities = new City[N];
        int[] minCost = new int[C+1];
        Arrays.fill(minCost,Integer.MAX_VALUE);

        PriorityQueue<City> pq = new PriorityQueue<>();

        for(int i=0;i<cities.length;i++){
            st = new StringTokenizer(br.readLine());
            cities[i] = new City(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

            if(cities[i].customer >= minCost.length){
                minCost[C] = Math.min(minCost[C],cities[i].cost);
            }
            else if(cities[i].cost < minCost[cities[i].customer]){
                minCost[cities[i].customer] = cities[i].cost;
                pq.add(new City(cities[i].cost,cities[i].customer));
            }
        }

        while(!pq.isEmpty()){
            City curr = pq.poll();

            if(curr.cost > minCost[curr.customer]){
                continue;
            }

            for(City city : cities){
                if(city.customer + curr.customer >= minCost.length){
                    if(minCost[C] > curr.cost + city.cost){
                        minCost[C] = curr.cost + city.cost;
                    }

                    continue;
                }
                if(minCost[city.customer + curr.customer] <= curr.cost + city.cost){
                    continue;
                }

                minCost[city.customer + curr.customer] = curr.cost + city.cost;
                pq.add(new City(minCost[city.customer + curr.customer],city.customer + curr.customer));
            }
        }

        System.out.println(minCost[C]);
    }
}
class City implements Comparable<City>{
    int cost;
    int customer;

    public City(int cost, int customer){
        this.cost = cost;
        this.customer = customer;
    }

    @Override
    public int compareTo(City o) {
        return this.cost - o.cost;
    }
}