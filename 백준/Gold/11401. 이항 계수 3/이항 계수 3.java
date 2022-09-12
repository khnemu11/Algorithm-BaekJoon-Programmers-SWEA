import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long N = Long.valueOf(st.nextToken());
		long K = Long.valueOf(st.nextToken());
		long nFactorial = 1, kFacotiral = 0, nMinusKFacotrial = 0;

		if (K == 0 || N == K) {
			bw.write(String.valueOf(1));
		} else {
			for (int i = 1; i <= N; i++) {
				nFactorial = (nFactorial * i) % 1000000007;
				if (i == K) {
					kFacotiral = nFactorial;
				} if (i == N - K) {
					nMinusKFacotrial = nFactorial;
				}
			}
			long under = nMinusKFacotrial * kFacotiral % 1000000007;
			long result = ((nFactorial % 1000000007) * (pow(under, 1000000007 - 2) % 1000000007)) % 1000000007;

			bw.write(String.valueOf(result));
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static long pow(long under, long exponent) {
		if (exponent == 1) {
			return under % 1000000007;
		} else {
			long temp = pow(under, exponent / 2);

			if (exponent % 2 == 1) {
				return (((temp * temp) % 1000000007) * (under % 1000000007)) % 1000000007;
			} else {
				return (temp * temp) % 1000000007;
			}
		}
	}
}
