import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Meat> pq = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            pq.add(new Meat(price,weight));
        }

        int minPrice = -1;
        int weight = 0;
        int totalPrice = 0;
        int beforePrice = 0;

        while(!pq.isEmpty()){
            Meat curr = pq.poll();

            weight += curr.weight;

            if(curr.price == beforePrice){
                totalPrice += curr.price;
            }else{
                totalPrice = curr.price;
            }

            if(weight >= M){
                if(minPrice == -1){
                    minPrice = totalPrice;
                }else {
                    minPrice = Math.min(totalPrice, minPrice);
                }
            }

            beforePrice = curr.price;
        }

        System.out.println(minPrice);
    }
}

class Meat implements Comparable<Meat>{
    int price;
    int weight;

    public Meat(int price ,int weight){
        this.price = price;
        this.weight = weight;
    }

    @Override
    public int compareTo(Meat o) {
        if(o.price == this.price){
            return o.weight - this.weight;
        }
        return this.price - o.price;
    }
}