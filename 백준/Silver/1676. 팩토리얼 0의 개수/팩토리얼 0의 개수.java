import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.valueOf(br.readLine());
		BigInteger num = factorial(n);
		String numStr = num.toString();
		int count=0;
		for(int i=numStr.length()-1;i>=0;i--) {
			if(numStr.charAt(i)!='0') {
				break;
			}
			count++;
		}
		bw.write(String.valueOf(count));
		bw.flush();
		br.close();
		bw.close();
	}
	public static BigInteger factorial (int n) {
		if(n==1 || n==0) {
			return new BigInteger(String.valueOf(1));
		}
		
		return factorial(n-1).multiply(new BigInteger(String.valueOf(n)));
	}
}