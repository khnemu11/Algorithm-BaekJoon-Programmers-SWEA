import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int nums[];
	static int sums[];
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int target = Integer.valueOf(st.nextToken());

		st = new StringTokenizer(br.readLine());
		nums = new int[N + 1];
		sums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
			sums[i] = nums[i] + sums[i - 1];
		}
		min = Integer.MAX_VALUE;
		if (sums[N] - sums[1] + nums[1] >= target) {
			min = N;
			for (int end = N; end > 0; end--) {
				if (end - (min - 1) + 1 < 1) {
					break;
				}
				for (int start = end - (min - 1) + 1; start <= end; start++) {
					if (sums[end] - sums[start] + nums[start] < target) {
						break;
					}
					min = Math.min(min, end - start + 1);
				}
			}
		}

		if (min == Integer.MAX_VALUE) {
			min = 0;
		}
		bw.write(String.valueOf(min));
		bw.newLine();
		bw.flush();
	}
}