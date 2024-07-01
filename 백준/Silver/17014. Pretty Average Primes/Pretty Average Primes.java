import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MAX_N = 2_000_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] isNotPrime = new boolean[MAX_N+1];
        List<Integer> primeList = new ArrayList<>();

        for(int i=2;i<isNotPrime.length;i++){
            if(isNotPrime[i]){
                continue;
            }
            primeList.add(i);

            for(int j=i*2;j<isNotPrime.length;j+=i){
                isNotPrime[j] = true;
            }
        }

        int T = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int testcase=1;testcase<=T;testcase++){
            int N = Integer.parseInt(br.readLine());

            for(int i=0;primeList.get(i) <= N;i++){
                int target = N*2 - primeList.get(i);

                if(!isNotPrime[target]){
                    bw.write(primeList.get(i)+" "+target);
                    bw.newLine();
                    break;
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}