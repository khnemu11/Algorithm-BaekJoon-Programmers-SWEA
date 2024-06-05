import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int testcase =1 ; testcase<=t;testcase++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[]num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Arrays.sort(num);

            int l = 0;
            int r = num.length-1;

            int minDiffer = Integer.MAX_VALUE;
            int count = 0;


            while(l<r){
                int sum = num[l] + num[r];
                int differ = Math.abs(k - sum);

                if(minDiffer > differ){
                    count = 1;
                    minDiffer = differ;
                }else if(minDiffer == differ){
                    count++;
                }

                if(sum < k){
                    l++;
                }else{
                    r--;
                }
            }

            System.out.println(count);
        }
    }
}