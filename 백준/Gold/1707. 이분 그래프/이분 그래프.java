import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.valueOf(st.nextToken());
			int E = Integer.valueOf(st.nextToken());

			boolean visited[] = new boolean[V + 1];
			int[] colors = new int[V + 1];

			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());

				graph.get(from).add(to);
				graph.get(to).add(from);
			}

			boolean isValid = true;

			for (int i = 1; i < graph.size(); i++) {
				if (!isValid) {
					break;
				}
				if (visited[i]) {
					continue;
				}

				Queue<Integer> q = new LinkedList<>();
				colors[i] = 1;
				q.add(i);

				while (!q.isEmpty() && isValid) {
					int from = q.poll();
					if (visited[from]) {
						continue;
					}

					visited[from] = true;

					for (int to : graph.get(from)) {
						if (colors[to] == 0) {
							colors[to] = colors[from] * -1;
							q.add(to);
						} else if (colors[to] == colors[from]) {
							isValid = false;
							break;
						}
					}
				}
			}

			if (isValid) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
}
