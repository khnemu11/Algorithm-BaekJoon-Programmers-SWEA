import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());


        List<Integer> threeCandy = new ArrayList<>();
        List<Integer> fiveCandy = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int sweat = Integer.parseInt(st.nextToken());

            if(weight == 3){
                threeCandy.add(sweat);
            }else{
                fiveCandy.add(sweat);
            }
        }

        Collections.sort(threeCandy,Collections.reverseOrder());
        Collections.sort(fiveCandy,Collections.reverseOrder());

        long[]preThreeSum = new long[threeCandy.size()+1];
        long[]preFiveSum = new long[fiveCandy.size()+1];

        for(int i=0;i<threeCandy.size();i++){
            preThreeSum[i+1] = preThreeSum[i] + threeCandy.get(i);
        }
        for(int i=0;i<fiveCandy.size();i++){
            preFiveSum[i+1] = preFiveSum[i] + fiveCandy.get(i);
        }

        int threeRightIdx = Math.min(threeCandy.size(), w/3);
        long max = preThreeSum[threeRightIdx];

        while(threeRightIdx >=0){
            int fiveRightIdx = Math.min((w - 3*threeRightIdx)/5, fiveCandy.size());
            max = Math.max(max,preThreeSum[threeRightIdx]+preFiveSum[fiveRightIdx]);

            threeRightIdx--;
        }

        System.out.println(max);
    }
}
