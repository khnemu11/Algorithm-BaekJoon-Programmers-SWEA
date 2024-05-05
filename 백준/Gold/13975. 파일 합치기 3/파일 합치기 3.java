import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testcase=1;testcase<=T;testcase++){
            int K = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            for(int i=0;i<K;i++){
                pq.add(Long.parseLong(st.nextToken()));
            }

            long result = 0;

            while(pq.size() > 1){
                long a = pq.poll();
                long b = pq.poll();

                long sum = a+ b;
                result +=sum;

                pq.add(sum);
            }

            System.out.println(result);
        }
    }
}