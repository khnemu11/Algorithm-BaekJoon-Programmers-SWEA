import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		long totalSum = 0;
		for (int i = 0; i < N; i++) {
			int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			long a = row[1];
			long b = row[0];
			long gcd = gcd(Math.max(a, b), Math.min(a, b));
			a = a / gcd;
			b = b / gcd;
			long expect = ((a % 1000000007) * (pow(b, 1000000007 - 2) % 1000000007)) % 1000000007;
			totalSum = (totalSum + expect) % 1000000007;
		}
		bw.write(String.valueOf(totalSum));
		bw.flush();
	}

	public static long pow(long base, int exp) {
		if (exp == 0) {
			return 1;
		} else if (exp == 1) {
			return base;
		}
		long half = pow(base, exp / 2);

		long result = ((half % 1000000007) * (half % 1000000007)) % 1000000007;

		if (exp % 2 == 0) {
			return result;
		} else {
			return ((result % 1000000007) * (base % 1000000007)) % 1000000007;
		}
	}

	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
}
