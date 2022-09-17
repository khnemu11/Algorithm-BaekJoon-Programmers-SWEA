import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		BigInteger total = new BigInteger(br.readLine());
		BigInteger more = new BigInteger(br.readLine());
		BigInteger a;
		BigInteger b;
		BigInteger two = new BigInteger("2");
		BigInteger one = new BigInteger("1");
		BigInteger zero = new BigInteger("0");
		
		if (total.subtract(more).mod(two).equals(zero)) {
			
			total = total.subtract(more);

			a = total.divide(two);
			b = a.add(more);
		} else {
			total = total.subtract(more).subtract(one);

			a = total.divide(two);
			b = a.add(more).add(one);
		}

		bw.write(b.toString());
		bw.newLine();
		bw.write(a.toString());
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
}
