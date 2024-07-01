import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_N = 2_000_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[]a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int q=0;q<Q;q++){
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;

            if(j-1< i){
                System.out.println(0);
            }else{
                int sum = 0;

                for(int r=i+1;r<=j;r++){
                    sum+=Math.abs(a[r]-a[r-1]);
                }

                System.out.println(sum);
            }
        }
    }
}