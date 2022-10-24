import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int total = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int size = Integer.valueOf(st.nextToken());
		int target = Integer.valueOf(st.nextToken());
		int nums[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		for (int i = 1; i <= size; i++) {
			dfs(nums, target, i, 0, 0, 0);
			
		}

		bw.write(String.valueOf(total));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}

	public static void dfs(int nums[], int target, int depth, int currDepth, int currIndex, int sum) {
		if (currDepth == depth) {
			
			if (sum == target) {
				total++;
			}
		} else {
			for (int i = currIndex; i < nums.length; i++) {
				sum += nums[i];
				dfs(nums, target, depth, currDepth + 1, i + 1, sum);
				sum -= nums[i];
			}
		}
	}
}