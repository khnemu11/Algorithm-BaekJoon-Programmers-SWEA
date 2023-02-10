import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	풀이 알고리즘
	파리채의 크기는 MXM으로 NXN이 넘지 않도록 좌표 N-M,N-M 까지만 진행하여 최대값을 구한다.
	이때 합은 구간합이기 때문에 구간합을 이용해 구한다.
*/

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.valueOf(st.nextToken());
			int M = Integer.valueOf(st.nextToken());

			int nums[][] = new int[N + 1][N + 1];
			int sums[][] = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					nums[i][j] = Integer.valueOf(st.nextToken());
					sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + nums[i][j];
				}
			}

			int max = 0;
			for (int i = 1; i + M - 1 <= N; i++) {
				for (int j = 1; j + M - 1 <= N; j++) {
					int sum = sums[i + M - 1][j + M - 1] - sums[i - 1][j + M - 1] - sums[i + M - 1][j - 1]
							+ sums[i - 1][j - 1];
					max = Math.max(max, sum);
				}
			}

			StringBuilder result = new StringBuilder();
			result.append("#").append(test_case).append(" ").append(max);
			System.out.println(result.toString());
		}
	}
}