import java.io.*;
import java.util.*;

class Main {
  static boolean visited[] = new boolean[2000001];
  static int nums[];
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.valueOf(br.readLine());
    nums = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).toArray();
    
    select(0,0);
    
    int num =1;

    while(true){
      if(!visited[num]){
        System.out.println(num);
        break;
      }
      num++;
    }
    
  }

  public static void select(int curr,int sum){
    if(curr>=nums.length){
      visited[sum]=true;
      return ;
    }
    visited[sum]=true;
     visited[sum+nums[curr]]=true;
    select(curr+1,sum);
    select(curr+1,sum+nums[curr]);
  }
}