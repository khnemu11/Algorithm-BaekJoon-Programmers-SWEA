import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 노드 개수
		int M = Integer.valueOf(st.nextToken()); // 간선 개수
		int start = Integer.valueOf(st.nextToken()); // 시작 노드 번호

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

		System.out.println(dfs(start));
		System.out.println(bfs(start));
		br.close();
	}

	public static String dfs(int start) {
		StringBuilder sb = new StringBuilder();
		boolean[] visited = new boolean[graph.size() + 1];

		dfs(start, visited, sb);

		return sb.deleteCharAt(sb.length() - 1).toString();
	}

	public static void dfs(int from, boolean visited[], StringBuilder sb) {	//dfs를 실행하는 메소드 (시작점, 방문배열, 방문한 노드 문자열)
		if (visited[from]) {
			return;
		}
		visited[from] = true;
		sb.append(from).append(" ");
		PriorityQueue<Integer> nextPQ = new PriorityQueue<>(); //작은 노드 부터 방문하기 위한 우선순위 큐

		for (int to : graph.get(from)) {
			if (visited[to]) {
				continue;
			}
			nextPQ.add(to);
		}

		while (!nextPQ.isEmpty()) { //작은 노드부터 방문
			dfs(nextPQ.poll(), visited, sb);
		}
	}

	public static String bfs(int start) { //bfs를 실행하는 메소드 (시작점)
		StringBuilder sb = new StringBuilder();
		boolean visited[] = new boolean[graph.size() + 1];
		Queue<Integer> q = new LinkedList<>();

		q.add(start);
		sb.append(start).append(" ");
		while (!q.isEmpty()) {
			int from = q.poll();
			visited[from] = true;

			PriorityQueue<Integer> nextPQ = new PriorityQueue<>();	//작은 노드 부터 방문하기 위한 우선순위 큐

			for (int to : graph.get(from)) {
				if (visited[to]) {
					continue;
				}

				visited[to] = true;
				nextPQ.add(to);
			}

			while (!nextPQ.isEmpty()) {	//작은 노드부터 방문
				int next = nextPQ.poll();
				sb.append(next).append(" ");
				q.add(next);
			}
		}

		return sb.deleteCharAt(sb.length() - 1).toString();
	}
}