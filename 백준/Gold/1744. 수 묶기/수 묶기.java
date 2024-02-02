import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> negativePQ = new PriorityQueue<>();
        PriorityQueue<Integer> positivePQ = new PriorityQueue<>(Collections.reverseOrder());

        int zeroCount = 0;

        for(int i=0;i<N;i++){
            int num = Integer.parseInt(br.readLine());

            if(num ==0){
                zeroCount++;
            }else if(num < 0){
                negativePQ.add(num);
            }else{
                positivePQ.add(num);
            }
        }

        int sum = 0;

        while(negativePQ.size() > 1){
            int a = negativePQ.poll();
            int b = negativePQ.poll();
            sum += a*b;
        }

        if(!negativePQ.isEmpty()){
            int num = negativePQ.poll();

            if(zeroCount == 0){
                sum += num;
            }
        }

        while(positivePQ.size() > 1){
            int a = positivePQ.poll();
            int b = positivePQ.poll();
            sum += Math.max(a * b, a + b);
        }

        if(!positivePQ.isEmpty()){
            sum += positivePQ.poll();
        }

        System.out.println(sum);
    }
}
