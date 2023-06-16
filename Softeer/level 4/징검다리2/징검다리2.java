import java.util.*;
import java.io.*;


public class Main{
    static int nums [];
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.valueOf(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).toArray();
        // System.out.println(Arrays.toString(nums));

        int increaseDp[] = getIncreaseDp();
        int decreaseDp[] = getDecreaseDp();

        int max = 1;

        for(int i=0;i<increaseDp.length;i++){
            max = Math.max(max,increaseDp[i] +decreaseDp[i]-1 );
        }
        bw.write(max+"\n");
        bw.flush();
    }

    public static int [] getIncreaseDp(){
        int dp[] = new int[nums.length];
        dp[0]=1;
        int maxNums[] = new int[nums.length];
        maxNums[0] = nums[0];
        int length = 1;

        for(int i=1;i<nums.length;i++){
            if(maxNums[length-1] < nums[i]){
                maxNums[length] = nums[i];
                length++;
            }else{ 
                int l=0;
                int r = length-1;
                while(l<r){
                    int mid = (l+r)/2;
                    // System.out.println(l+" ->"+r+"mid : "+mid);

                    if(nums[i] > maxNums[mid]){
                        l = mid+1;
                    }else{
                        r = mid;
                    }
                }
                maxNums[l] = nums[i];
            }
            dp[i]=length;
            // System.out.println(Arrays.toString(maxNums));
        }
        // System.out.println(Arrays.toString(dp));
        return dp;
    }
    public static int [] getDecreaseDp(){
        int dp[] = new int[nums.length];
        dp[nums.length-1]=1;
        int maxNums[] = new int[nums.length];
        maxNums[0] = nums[nums.length-1];
        int length = 1;

        for(int i=nums.length-1;i>=0;i--){
            if(maxNums[length-1] < nums[i]){
                maxNums[length] = nums[i];
                length++;
            }else{ 
                int l=0;
                int r = length-1;
                while(l<r){
                    int mid = (l+r)/2;
                    // System.out.println(l+" ->"+r+"mid : "+mid);

                    if(nums[i] > maxNums[mid]){
                        l = mid+1;
                    }else{
                        r = mid;
                    }
                }
                maxNums[l] = nums[i];
            }
            dp[i]=length;
            // System.out.println(Arrays.toString(maxNums));
        }
        // System.out.println(Arrays.toString(dp));
        return dp;
    }
}
