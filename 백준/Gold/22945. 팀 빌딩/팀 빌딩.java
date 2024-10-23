import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] developers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int l = 0;
        int r = N-1;

        int max = Math.min(developers[l],developers[r]) * (r-l-1);

        while(l<r){
            int val = Math.min(developers[l],developers[r]) * (r-l-1);
            max = Math.max(max,val);

            int min = Math.min(developers[l],developers[r]);

            if(developers[l] < developers[r]){
                l++;
            }else{
                r--;
            }
        }

        System.out.println(max);
    }
}