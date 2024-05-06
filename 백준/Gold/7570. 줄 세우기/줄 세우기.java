import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] length = new int[N+1];
        int max = 0;

        for(int i=0;i<num.length;i++){
            length[num[i]] = length[num[i]-1]+1;
            max = Math.max(max,length[num[i]]);
        }

        System.out.println(N - max);
    }
}