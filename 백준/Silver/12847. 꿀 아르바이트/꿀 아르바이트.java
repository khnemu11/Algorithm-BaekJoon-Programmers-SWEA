import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] money = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long sum = 0;

        for(int i=0;i<m;i++){
            sum+=money[i];
        }

        long max = sum;

        for(int end=m;end<n;end++){
            sum-=money[end-m];
            sum+=money[end];

            max = Math.max(sum,max);
        }

        System.out.println(max);
    }
}