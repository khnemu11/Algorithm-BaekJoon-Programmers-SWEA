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

		int size = Integer.valueOf(br.readLine());
		int num[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int dp[] = new int[size];
		Arrays.fill(dp, 1);
		int max = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < i; j++) {
				if (num[i] < num[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}

			}
			max = Math.max(dp[i], max);
		}

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
	}
}
