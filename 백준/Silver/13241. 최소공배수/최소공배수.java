import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        BigInteger A = new BigInteger(input[0]);
        BigInteger B = new BigInteger(input[1]);
        BigInteger gcd = A.gcd(B);

        BigInteger lcm = A.multiply(B).divide(gcd);

        System.out.println(lcm.toString());
    }
}