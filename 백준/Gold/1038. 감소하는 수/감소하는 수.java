import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static long val;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		int digit = 1;
		while (cnt < N && digit < 10) {
			dfs(0, digit, new int[digit], N);
			digit++;
		}
		if (cnt + 1 == N) {
			System.out.println("9876543210");
		} else if (cnt == N) {
			System.out.println(val);
		}else {
			System.out.println("-1");
		}
	}

	public static void dfs(int curr, int digit, int[] nums, int N) {
		if (cnt >= N) {
			return;
		} else if (curr == digit) {

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < nums.length; i++) {
				sb.append(nums[i]);
			}

			long sum = Long.valueOf(sb.toString());

			if (sum != 0) {
				val = Long.valueOf(sb.toString());
				cnt++;
			}
		} else {
			for (int i = 0; i < 10; i++) {
				if (curr > 0 && nums[curr - 1] <= i) {
					continue;
				}
				nums[curr] = i;
				dfs(curr + 1, digit, nums, N);
			}
		}
	}
}
