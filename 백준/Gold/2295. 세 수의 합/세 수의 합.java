import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        Set<Integer> sets = new HashSet<>();
        
        for(int i=0;i<N;i++){
            int n = Integer.parseInt(br.readLine());
            nums[i] = n;
        }
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
                sets.add(nums[i] + nums[j]);
            }
        }

        int max =-1;

        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                if(sets.contains(nums[i] - nums[j])){
                    max = Math.max(max,nums[i]);
                }
            }
        }

        System.out.println(max);
    }
}
