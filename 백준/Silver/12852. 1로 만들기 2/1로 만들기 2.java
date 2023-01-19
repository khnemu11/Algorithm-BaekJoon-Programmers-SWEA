import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int dp[];
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int target = Integer.valueOf(br.readLine());
		int dp[] = new int[target + 1];
		int op[] = new int[target + 1];
		boolean visited[] = new boolean[target + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		int cnt = 1;

		while (!q.isEmpty()) {
			int loop = q.size();
			for (int i = 0; i < loop; i++) {
				int curr = q.poll();
				visited[curr] = true;
				dp[curr] = cnt;

				if (curr == target) {
					break;
				}

				if (curr * 3 <= target && !visited[curr * 3]) {
					visited[curr * 3] = true;
					q.add(curr * 3);
					op[curr * 3] = 3;
				}
				if (curr * 2 <= target && !visited[curr * 2]) {
					visited[curr * 2] = true;
					q.add(curr * 2);
					op[curr * 2] = 2;
				}
				if (curr + 1 <= target && !visited[curr + 1]) {
					visited[curr + 1] = true;
					q.add(curr + 1);
					op[curr + 1] = 1;
				}
			}
			cnt++;
		}
		StringBuilder sb = new StringBuilder();

		int idx = target;
		while (idx > 1) {
			sb.append(idx + " ");

			if (op[idx] == 3) {
				idx /= 3;
			} else if (op[idx] == 2) {
				idx /= 2;
			} else {
				idx--;
			}
		}

		sb.append("1");
		System.out.println(dp[target] - 1);
		System.out.println(sb.toString());
	}
}
