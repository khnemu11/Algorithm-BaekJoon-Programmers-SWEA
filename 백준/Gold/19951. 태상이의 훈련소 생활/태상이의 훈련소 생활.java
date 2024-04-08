import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] ground = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] preSum = new int[N+1];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) -1;
            int end = Integer.parseInt(st.nextToken()) -1;
            int height = Integer.parseInt(st.nextToken());

            preSum[start]+=height;
            preSum[end+1]-=height;
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<preSum.length-1;i++){
            preSum[i+1] =preSum[i] + preSum[i+1];
            sb.append((preSum[i] + ground[i])+" ");
        }

        System.out.println(sb);
    }
}
