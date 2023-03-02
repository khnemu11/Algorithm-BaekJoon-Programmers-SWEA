import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 * 풀이 알고리즘
    1) 우선순위 큐를 활용한 다익스트라 알고리즘을 활용해서 최단거리 구하고 모두 출력
*/

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int E = Integer.valueOf(st.nextToken());
		int start = Integer.valueOf(br.readLine());

		ArrayList<ArrayList<Node>> graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			graph.get(from).add(new Node(to, cost));
		}

		int path[] = new int[N + 1];

		int INF = 100000000;
		Arrays.fill(path, INF);
		path[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node from = pq.poll();

			if (from.cost > path[from.val]) {
				continue;
			}

			for (Node to : graph.get(from.val)) {
				if (path[to.val] > path[from.val] + to.cost) {
					path[to.val] = path[from.val] + to.cost;
					pq.add(new Node(to.val, path[to.val]));
				}
			}
		}

		for (int i = 1; i < path.length; i++) {
			if (path[i] == INF) {
				bw.write("INF");
			} else {
				bw.write("" + path[i]);
			}
			bw.newLine();
		}

		bw.flush();
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