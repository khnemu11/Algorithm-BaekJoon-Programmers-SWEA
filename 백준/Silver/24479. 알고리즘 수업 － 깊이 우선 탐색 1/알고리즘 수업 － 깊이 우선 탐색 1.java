import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int visited[];
	static ArrayList<ArrayList<Integer>> graph;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		int start = Integer.valueOf(st.nextToken());
		visited = new int[N + 1];
		graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());

			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		for (int i = 1; i <= N; i++) {
			graph.get(i).sort((x, y) -> x - y);
		}
		cnt = 1;
		dfs(start);

		for (int i = 1; i < visited.length; i++) {
			System.out.println(visited[i]);
		}
	}

	public static void dfs(int from) {
		visited[from] = cnt;

		for (int to : graph.get(from)) {
			if (visited[to] > 0) {
				continue;
			}
			cnt++;

			dfs(to);
		}
	}

}
