import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
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
            
            //j-1<1 인경우는 0 출력
            if(j-1< i){
                System.out.println(0);
            }
            //아닌경우는 누적합 실행
            else{
                int sum = 0;

                for(int r=i+1;r<=j;r++){
                    sum+=Math.abs(a[r]-a[r-1]);
                }

                System.out.println(sum);
            }
        }
    }
}