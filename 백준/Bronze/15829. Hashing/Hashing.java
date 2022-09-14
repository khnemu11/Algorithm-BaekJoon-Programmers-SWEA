import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.valueOf(br.readLine());

		long total = 0;

		for (int i = 0; i < size; i++) {
			total = (total + (br.read() - 96) * pow(31, i) % 1234567891) % 1234567891;
		}
		bw.write(String.valueOf(total));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

	public static long pow(int base, int exponent) {
		if (exponent == 0) {
			return 1;
		} else if (exponent == 1) {
			return base;
		}

		long temp = pow(base, exponent / 2);

		if (exponent % 2 == 1) {
			return (((temp * temp) % 1234567891) * (base % 1234567891)) % 1234567891;
		} else {
			return temp * temp % 1234567891;
		}
	}
}
