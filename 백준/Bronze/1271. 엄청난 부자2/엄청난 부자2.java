import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BigInteger n = new BigInteger(st.nextToken());
		BigInteger m = new BigInteger(st.nextToken());
		
		BigInteger money = n.divide(m);
		BigInteger rest = n.mod(m);
		
		bw.write(money.toString());
		bw.newLine();
		bw.write(rest.toString());
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
}
