
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static long max;
	static Boolean visited[];
	static boolean graph[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int node = sc.nextInt();
			int edge = sc.nextInt();

			graph = new boolean[node + 1][node + 1];
			visited = new Boolean[node + 1];
			Arrays.fill(visited, false);
			for (int i = 0; i < edge; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();

				graph[start][end] = true;
				graph[end][start] = true;
			}

			max = 1;

			for (int i = 1; i <= node; i++) {
				dfs(i);
			}

			StringBuilder result = new StringBuilder();
			result.append("#").append(test_case).append(" ").append(max);
			System.out.println(result.toString());
		}
	}

	public static void dfs(int start) {
		boolean isNext = false;
		for (int i = 1; i < graph.length; i++) {
			if (visited[i] || !graph[start][i]) {
				continue;
			}
			isNext = true;
			visited[i] = true;
			dfs(i);
			visited[i] = false;
		}

		if (!isNext) {
			long sum = Arrays.stream(visited).filter(x -> x == true).count();
			max = Math.max(sum, max);
		}
	}

}
