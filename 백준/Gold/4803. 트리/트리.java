import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[];
	static List<List<Integer>> graph;
	static boolean isCycle;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int M = Integer.valueOf(st.nextToken());

			if (N == 0 && M == 0) {
				break;
			}
			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			visited = new boolean[N + 1];

			while (M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());

				graph.get(from).add(to);
				graph.get(to).add(from);
			}

			int cnt = 0;

			for (int i = 1; i <= N; i++) {
				isCycle = false;

				if (visited[i]) {
					continue;
				}

				dfs(i, 0);

				if (!isCycle) {
					cnt++;
				}
			}

			StringBuilder resultBuilder = new StringBuilder();
			resultBuilder.append("Case ").append(test_case).append(": ").append(treeString(cnt));

			System.out.println(resultBuilder.toString());
			test_case++;
		}
	}

	public static void dfs(int from, int before) {
		if (visited[from]) {
			return;
		}
		visited[from] = true;
		for (int to : graph.get(from)) {
			if (to == before) {
				continue;
			}

			if (visited[to]) {
				isCycle = true;
				continue;
			}

			dfs(to, from);
		}
	}

	public static String treeString(int size) {
		if (size == 0) {
			return "No trees.";
		} else if (size == 1) {
			return "There is one tree.";
		} else {
			return "A forest of " + size + " trees.";
		}
	}
}
