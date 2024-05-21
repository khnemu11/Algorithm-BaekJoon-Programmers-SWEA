import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int testcase =0 ;testcase<T;testcase++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[]jewels = new int[N*2];

            st = new StringTokenizer(br.readLine());

            int sum = 0;

            for(int i=0;i<N;i++){
                int jewel = Integer.parseInt(st.nextToken());

                if(i < M){
                    sum+=jewel;
                }

                jewels[i] = jewel;
                jewels[i+N] =jewel;
            }

            int count = sum < K ? 1 : 0;

            int l = 0;
            int r = M;

            if(M != N){
                while(M!=N && l+1<N){
                    sum = sum - jewels[l] + jewels[r];

                    if(sum < K){
                        count++;
                    }

                    l++;
                    r++;
                }
            }
            
            System.out.println(count);
        }
    }
}

