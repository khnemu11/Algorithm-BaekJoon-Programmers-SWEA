import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	정점의 개수(1,000)가 간선(100,000)보다 작음 ->프림
	최대 비용 :1,000* 10,000 = 1억
*/
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int V = Integer.valueOf(br.readLine());
		int E = Integer.valueOf(br.readLine());

		boolean visited[] = new boolean[V + 1];
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}

		int start = 1; // 1 정점부터 시작
		PriorityQueue<Node> q = new PriorityQueue<>(); // 가중치에 따라 정렬한 큐
		q.add(new Node(start, 0));

		int cnt = 0; // 방문한 노드 개수
		int cost = 0;

		while (!q.isEmpty()) { // 프림 알고리즘 시작
			Node curr = q.poll();

			if (visited[curr.val]) {
				continue;
			}

			visited[curr.val] = true;
			cost += curr.cost;
			cnt++;

			if (cnt == V) { // 방문한 노드의 개수가 노드의 개수와 같다면 프림 탈출
				break;
			}

			for (Node to : graph.get(curr.val)) {
				if (visited[to.val]) {
					continue;
				}
				q.add(to);
			}
		}

		System.out.println(cost);
	}
}

class Node implements Comparable<Node> {
	int val;
	int cost;

	public Node(int val, int cost) {
		this.val = val;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}