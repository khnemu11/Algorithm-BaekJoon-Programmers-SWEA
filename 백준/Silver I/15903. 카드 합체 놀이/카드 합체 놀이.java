import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        PriorityQueue<Long> pq = new PriorityQueue<>();
        input = br.readLine().split(" ");

        for(int i=0;i<input.length;i++){
            pq.add(Long.parseLong(input[i]));
        }

        int round = 0;

        while(round++<m){
            long x = pq.poll();
            long y = pq.poll();

            long sum = x+y;

            pq.add(sum);
            pq.add(sum);
        }

        long sum = 0;

        while(!pq.isEmpty()){
            sum+=pq.poll();
        }

        System.out.println(sum);
    }
}