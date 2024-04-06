import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] trafficLight = new int[N+1];

        for(int i=0;i<B;i++){
            int n = Integer.parseInt(br.readLine());
            trafficLight[n] = 1;
        }

        int fixCount = 0;

        for(int i=0;i<K;i++){
            fixCount+=trafficLight[i];
        }

        int min = K;

        for(int i=1;i+K-1<trafficLight.length;i++){
            fixCount-=trafficLight[i-1];
            fixCount+=trafficLight[i+K-1];

            min = Math.min(min,fixCount);
        }

        System.out.println(min);
    }
}