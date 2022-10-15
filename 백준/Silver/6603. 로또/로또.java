import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.valueOf(st.nextToken());
			if (k == 0) {
				break;
			}

			int set[] = new int[k];
			visited = new boolean[k];
			for (int i = 0; i < k; i++) {
				set[i] = Integer.valueOf(st.nextToken());
			}

			dfs(6, 0, set, 0);

			System.out.println();
		}

		br.close();
	}

	static void dfs(int depth, int curr, int num[], int max) {
		if (depth == curr) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					sb.append(num[i]);
					sb.append(" ");
				}
			}

			System.out.println(sb.toString());
		} else {
			for (int i = max; i < visited.length; i++) {
				if (!visited[i]) {
					visited[i] = true;
					dfs(depth, curr + 1, num, i + 1);
					visited[i] = false;
				}
			}
		}
	}
}