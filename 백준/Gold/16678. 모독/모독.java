import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        int damage = 1;
        long cost = 0;

        while(!pq.isEmpty()){
            int curr = pq.poll();

            if(damage > curr){
                continue;
            }

            cost += curr - damage;
            damage++;
        }

        System.out.println(cost);
    }
}