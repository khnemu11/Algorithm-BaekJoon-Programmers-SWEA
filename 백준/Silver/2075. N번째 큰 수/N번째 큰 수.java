import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    final static int MOD = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<N;i++){
            pq.addAll(Arrays.asList(Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).toArray(Integer[]::new)));
        }

        for(int i=0;i<N-1;i++){
            pq.poll();
        }

        System.out.println(pq.poll());
    }
}
