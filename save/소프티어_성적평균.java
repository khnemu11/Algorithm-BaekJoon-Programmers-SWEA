import java.util.*;
import java.io.*;


public class Main{
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int num[] = new int[N];
        int sums[] = new int[N+1];
        
        st= new StringTokenizer(br.readLine());
        
        for(int i=0;i<N;i++){
            num[i] = Integer.parseInt(st.nextToken());
            sums[i+1] = sums[i] + num[i];
        }

        for(int i=0;i<K;i++){
            st= new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            int n = end - start + 1;    //총 개수
            int sum = sums[end]-sums[start-1];    //합
            double avg = (double)sum/n;
            String str = String.format("%.2f",avg);
            System.out.println(str);
        }
    }
}
