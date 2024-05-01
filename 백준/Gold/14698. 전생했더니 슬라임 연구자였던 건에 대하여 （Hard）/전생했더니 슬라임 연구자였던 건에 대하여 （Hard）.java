import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testcase = 1; testcase<=T;testcase++){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            for(int i=0;i<N;i++){
                long slime = Long.parseLong(st.nextToken());
                pq.add(slime);
            }

            long cost = 1;

            while(pq.size() > 1){
                long a = pq.poll();
                long b = pq.poll();
                long mix = a*b;

                cost = ((cost%1_000_000_007)*(mix%1_000_000_007))%1_000_000_007;

                pq.add(mix);
            }

            System.out.println(cost);

        }
    }
}
