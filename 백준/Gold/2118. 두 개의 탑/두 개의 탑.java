import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] distance = new int[N];
        int total = 0;

        for(int i=0;i<distance.length;i++){
            distance[i] = Integer.parseInt(br.readLine());
            total += distance[i];
        }

        int l=0,r=0;
        int max = 0;

        int curr = distance[l];

        while(l <=r && r < N){
            int minDistance = Math.min(curr, total - curr);
            max = Math.max(max,minDistance);

            if(curr == minDistance){
                r++;
                if(r<N){
                    curr += distance[r];
                }
            }else{
                curr -=distance[l];
                l++;
            }
        }

        System.out.println(max);
    }
}
