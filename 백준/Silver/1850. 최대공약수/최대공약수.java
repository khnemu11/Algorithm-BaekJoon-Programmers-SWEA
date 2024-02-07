import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] agrs) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		BigInteger A = new BigInteger(input[0]);
		BigInteger B = new BigInteger(input[1]);
		
		BigInteger gcd = A.gcd(B);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<gcd.intValue();i++) {
			sb.append("1");
		}
		
		System.out.println(sb.toString());
	}
}