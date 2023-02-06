import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
	풀이 과정
	모든 가중치의 합 - mst의 최소값을 구하는 문제
	프림 알고리즘을 활용하여 mst를 구하자
	
*/

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.valueOf(st.nextToken());
			int E = Integer.valueOf(st.nextToken());

			if (V == 0 && E == 0) {
				break;
			}

			ArrayList<ArrayList<Node>> graph = new ArrayList<>();

			for (int i = 0; i < V; i++) {
				graph.add(new ArrayList<>());
			}

			int originalTotal = 0;

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				int cost = Integer.valueOf(st.nextToken());

				originalTotal += cost;

				graph.get(from).add(new Node(to, cost));
				graph.get(to).add(new Node(from, cost));
			}

			int sum = 0, cnt = 0;
			boolean visited[] = new boolean[V];
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0, 0));

			while (!pq.isEmpty() && cnt < V) {
				Node curr = pq.poll();
				if (visited[curr.val]) {
					continue;
				}

				visited[curr.val] = true;
				cnt++;
				sum += curr.cost;

				for (Node to : graph.get(curr.val)) {
					if (visited[to.val]) {
						continue;
					}

					pq.add(to);
				}
			}

			bw.write((originalTotal - sum) + "\n");
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