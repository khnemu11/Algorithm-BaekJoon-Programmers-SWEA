import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int n = 1;
        int target = 1;

        while(true){
            if(n>N){
                n/=2;
                break;
            }
//            System.out.println(n);
            n*=2;
        }
//        System.out.println(n);
        while(N > 0 && K > 0){
            N -= n;
            K--;
            target = n;
//            System.out.println(N+" 남은 횟수 : "+K+" "+n);
            while(N < n){
                n/=2;
            }
        }

//        System.out.println(N +"->"+target);
        if(N == 0){
            System.out.println(0);
        }else{
            System.out.println(target - N);
        }

    }
}