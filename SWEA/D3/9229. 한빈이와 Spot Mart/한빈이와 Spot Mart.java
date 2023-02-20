import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			int snacks[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			int max = 0;

			for (int i = 0; i < snacks.length - 1; i++) {
				for (int j = i + 1; j < snacks.length; j++) {
					int sum = snacks[i] + snacks[j];
					if (sum <= weight) {
						max = Math.max(max, sum);
					}
				}
			}

			StringBuilder sb = new StringBuilder();

			sb.append("#").append(test_case).append(" ");

			if (max == 0) {
				sb.append(-1).append("\n");
			} else {
				sb.append(max).append("\n");
			}
			bw.write(sb.toString());
		}
		bw.flush();
	}
}