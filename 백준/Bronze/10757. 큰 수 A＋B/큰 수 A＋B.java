import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		BigInteger bigInteger1 = new BigInteger(input[0]);
		BigInteger bigInteger2 = new BigInteger(input[1]);
		
		System.out.println(bigInteger1.add(bigInteger2));
	}
       
}