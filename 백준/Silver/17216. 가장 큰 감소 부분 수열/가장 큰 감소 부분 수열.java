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
		int nums[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int dp[] = new int[size];
		int max = 0;
		for (int curr = 0; curr < nums.length; curr++) {
			dp[curr] = nums[curr];
			for (int before = 0; before < curr; before++) {
				if (nums[before] > nums[curr]) {
					dp[curr] = Math.max(dp[before] + nums[curr], dp[curr]);
				}
			}
			max = Math.max(dp[curr], max);
		}
		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
	}
}
