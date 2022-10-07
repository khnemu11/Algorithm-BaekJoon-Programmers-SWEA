import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.valueOf(st.nextToken());
		int length = Integer.valueOf(st.nextToken());
		int combination[] = new int[length];
		int nums[] = new int[size];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < size; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
		}
		Arrays.sort(nums);
		dfs(nums, combination, 0, length, -1);
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int nums[], int combination[], int depth, int length, int curr) {
		if (depth == length) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < length; i++) {
				sb.append(combination[i]);
				sb.append(" ");
			}
			System.out.println(sb.toString());
		} else {
			for (int i = curr + 1; i < nums.length; i++) {
				combination[depth] = nums[i];
				dfs(nums, combination, depth + 1, length, i);
			}
		}
	}
}
