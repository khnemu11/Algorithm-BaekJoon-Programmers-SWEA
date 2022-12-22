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
		int num[] = new int[100001];
		Arrays.fill(num, -1);

		num[2] = 1;
		num[4] = 2;
		num[5] = 1;

		for (int i = 6; i <= N; i++) {
			int useTwo = -1;
			int useFive = -1;

			if (i - 2 > 0 && num[i - 2] != -1) {
				useTwo = num[i - 2];
			}
			if (i - 5 > 0 && num[i - 5] != -1) {
				useFive = num[i - 5];
			}

			if (useFive == -1 && useTwo == -1) {
				num[i] = -1;
			} else if (useFive == -1 || useTwo == -1) {
				num[i] = Math.max(useTwo, useFive) + 1;
			} else if (useFive != -1 && useTwo != -1) {
				num[i] = Math.min(useTwo, useFive) + 1;
			}
		}
		System.out.println(num[N]);
	}
}
