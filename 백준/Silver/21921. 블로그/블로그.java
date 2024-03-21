import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long sum = 0;

        for(int i=0;i<X;i++){
            sum +=num[i];
        }

        long max = sum;
        int count = 1;


        for(int start=1;start+X-1<num.length;start++){
            sum = sum - num[start-1] + num[start+X-1];

            if(sum > max){
                max = sum;
                count=1;
            }else if(sum == max){
                count++;
            }
        }

        if(sum == 0L) {
            System.out.println("SAD");
        }else{
            System.out.println(max);
            System.out.println(count);
        }
    }
}
