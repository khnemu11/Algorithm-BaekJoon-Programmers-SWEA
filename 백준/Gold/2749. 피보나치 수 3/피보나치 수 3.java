import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class Main {
    final static int MOD = 1_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        BigInteger N = new BigInteger(br.readLine());

        long pisano = 15 * MOD / 10;
        int target = N.mod(new BigInteger(String.valueOf(pisano))).intValue();
        int[] fibonacci = new int[target+1];

        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for(int i=2;i<fibonacci.length;i++){
            fibonacci[i] = (fibonacci[i-1] + fibonacci[i-2]) % MOD;
        }

        System.out.println(fibonacci[target]);
    }
}