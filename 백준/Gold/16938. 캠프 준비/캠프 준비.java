import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,L,R,X;
    static boolean[]selected;
    static int[]levels;
    static long count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        selected = new boolean[N];
        levels = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        select(0);

        System.out.println(count);
    }

    public static void select(int n){
        if(n == selected.length){
            int sum = 0;
            long min = Integer.MAX_VALUE;
            long max = Integer.MIN_VALUE;
            int selectCnt = 0;

            for(int i=0;i<selected.length;i++){
                if(selected[i]){
                    selectCnt++;
                    sum+=levels[i];

                    max = Math.max(max,levels[i]);
                    min = Math.min(min,levels[i]);
                }
            }

            if(selectCnt >= 2 && max - min >= X && sum >= L && sum <= R){
                count++;
            }
            return;
        }

        select(n+1);

        selected[n] = true;
        select(n+1);
        selected[n] = false;
    }
}