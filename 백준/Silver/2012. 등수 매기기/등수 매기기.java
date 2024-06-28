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

        long sum = 0;
        int grade = 1;

        while(!pq.isEmpty()){
            int curr = pq.poll();

            sum += Math.abs(grade - curr);
            grade++;
        }

        System.out.println(sum);
    }
}