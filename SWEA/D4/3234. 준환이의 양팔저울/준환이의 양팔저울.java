import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution {
	static int count;
	static int weightArr[];
	static boolean visited[];
	static double total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			visited = new boolean[N];

			weightArr = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			count = 0;
			total = Arrays.stream(weightArr).sum();
			pickWeight(0, N, 0, 0);

			StringBuilder sb = new StringBuilder();
			sb.append("#" + test_case + " " + count);
			bw.write(sb.toString());
			bw.newLine();
		}

		bw.flush();
	}

	public static void pickWeight(int curr, int N, int rightSum, int leftSum) {
		if (curr == N) {
			count++;
		} else {
			if (total / 2.0 <= Double.valueOf(leftSum)) {
				count += factorial(N - curr) * Math.pow(2, N - curr);
			} else {
				for (int i = 0; i < N; i++) {
					if (visited[i]) {
						continue;
					} else {
						visited[i] = true;
						pickWeight(curr + 1, N, rightSum, leftSum + weightArr[i]);
						if (weightArr[i] + rightSum <= leftSum) {
							pickWeight(curr + 1, N, rightSum + weightArr[i], leftSum);
						}
					}
					visited[i] = false;
				}
			}
		}
	}
	public static int factorial(int n) {
		if (n == 0 || n == 1) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}
}