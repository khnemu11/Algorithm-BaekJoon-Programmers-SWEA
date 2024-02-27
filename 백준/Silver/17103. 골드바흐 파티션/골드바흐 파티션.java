import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static final int MAX = 1_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        boolean[] notPrime = getIsNotPrimeArr();

        for(int testcase=1;testcase<=T;testcase++){
            int n = Integer.parseInt(br.readLine());
            int count = 0;

            for(int i=2;i<=n/2;i++){
                if(!notPrime[i] && !notPrime[n-i]){
                    count++;
                }
            }

            System.out.println(count);
        }

    }

    public static boolean[] getIsNotPrimeArr(){
        boolean[] notPrime = new boolean[MAX+1];

        for(int i=2;i<notPrime.length;i++){
            if(notPrime[i]){
                continue;
            }

            for(int j=i*2;j<notPrime.length;j+=i){
                notPrime[j] = true;
            }
        }

        return notPrime;
    }
}