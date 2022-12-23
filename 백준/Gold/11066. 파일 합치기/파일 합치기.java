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

		int T = Integer.valueOf(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int size = Integer.valueOf(br.readLine());
			int num[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			int dp[][] = new int[size][size];
			int sum[] = new int[size];

			sum[0] = num[0];
			for (int i = 1; i < size; i++) {
				sum[i] = sum[i - 1] + num[i];
			}

			for (int gap = 1; gap < size; gap++) {
				for (int start = 0; start + gap < size; start++) {
					dp[start][start + gap] = Integer.MAX_VALUE;
					for (int mid = start; mid < start + gap; mid++) {
						dp[start][start + gap] = Math.min(dp[start][start + gap],
								dp[start][mid] + dp[mid + 1][start + gap] + sum[start + gap] - sum[start] + num[start]);
					}
				}
			}

			System.out.println(dp[0][size-1]);
		}

	}
}
