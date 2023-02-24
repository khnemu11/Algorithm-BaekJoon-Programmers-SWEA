import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
	A는 B와 친구다.
	B는 C와 친구다.
	C는 D와 친구다.
	D는 E와 친구다.
	->깊이가 4 이상인 관계가 존재한다.
	->dfs로 깊이 탐색
*/
public class Main {
	static int maxDepth;
	static boolean visited[];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		for (int i = 0; i < N; i++) { // 방문하지 않는 노드의 깊이 탐색
			visited = new boolean[N];
			dfs(i, 0);

			if (maxDepth >= 4) {
				break;
			}
		}

		if (maxDepth >= 4) { // 최대 깊이가 4이상이면 조건의 친구 관계 존재
			bw.write("1\n");
		} else {
			bw.write("0\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int curr, int depth) { // dfs를 이용해 다음 노드로 움직이는 메소드 (현재 노드, 현재 까지의 깊이)
		maxDepth = Math.max(maxDepth, depth);
		if (maxDepth >= 4) {
			return;
		}

		visited[curr] = true;

		for (int child : graph.get(curr)) {
			if (visited[child]) {
				continue;
			}
			visited[child] = true;
			dfs(child, depth + 1);
			visited[child] = false;
		}
	}
}