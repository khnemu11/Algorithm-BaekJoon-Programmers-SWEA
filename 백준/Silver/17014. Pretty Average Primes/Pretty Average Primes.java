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
        
        //에라토스테네스의 체로 소수 판정 및 소수 리스트 생성
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

        //모든 테스트 케이스를 받아 A와 B 탐색
        for(int testcase=1;testcase<=T;testcase++){
            int N = Integer.parseInt(br.readLine());

            //A와 B가 모두 소수이고 (A*B)/2 = N을 만족하는지 판정
            for(int i=0;primeList.get(i) <= N;i++){
                int A = primeList.get(i);
                int B = N*2 - primeList.get(i);
                
                //B가 소수이면 주어진 조건을 만족하므로 출력
                if(!isNotPrime[B]){
                    bw.write(A+" "+B);
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