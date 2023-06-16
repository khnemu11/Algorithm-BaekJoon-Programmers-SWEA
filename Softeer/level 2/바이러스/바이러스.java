import java.util.*;
import java.io.*;


public class Main{
    static long MOD = 1_000_000_007;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());   //처음 바이러스 수
        int P = Integer.parseInt(st.nextToken());   //늘어나느 배수
        int N = Integer.parseInt(st.nextToken());   //늘어나는 횟수

        long val = pow(P,N);
        long result = multiMod(K,val);

        System.out.println(result);
    }
    public static long multiMod(long a,long b){
        return ((a%MOD)*(b%MOD))%MOD;
    }
    public static long pow(long n,int k){
        if(k==1){
            return n;
        }

        long half = pow(n,k/2);
        long val = multiMod(half,half);
        //짝수인 경우
        if(k%2==0){
            return val;
        }
        //홀수인 경우
        else{
            return multiMod(val,n);
        }
    }
}
