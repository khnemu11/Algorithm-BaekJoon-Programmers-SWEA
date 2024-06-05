import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[]solution = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        int val = Integer.MAX_VALUE;
        int l = 0;
        int r = N-1;

        while(l<r){
            int sum = solution[l] + solution[r];

            val = Math.min(Math.abs(val),Math.abs(sum)) == Math.abs(val) ? val : sum;

            if(sum == 0){
                break;
            }else if(sum < 0){
                l++;
            }else{
                r--;
            }
        }

        System.out.println(val);
    }
}