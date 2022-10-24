import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int node = Integer.valueOf(br.readLine());
		int edge = Integer.valueOf(br.readLine());
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();

		for (int i = 0; i <= node; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < edge; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			graph.get(start).add(new Node(end, cost));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());

		int start = Integer.valueOf(st.nextToken());
		int end = Integer.valueOf(st.nextToken());

		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, 0));
		
		boolean visited[] = new boolean[node + 1];
		int distance[] = new int[node + 1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		while (!queue.isEmpty()) {
			Node curr = queue.poll();

			if (visited[curr.node]) {
				continue;
			}

			visited[curr.node] = true;

			for (Node next : graph.get(curr.node)) {
				if (curr.cost + next.cost < distance[next.node]) {
					distance[next.node] = curr.cost + next.cost;
					queue.add(new Node(next.node, distance[next.node]));
				}
			}
			
		}

		bw.write(String.valueOf(distance[end]));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}

}

class Node implements Comparable<Node> {
	int node;
	int cost;

	public Node(int node, int cost) {
		this.node = node;

		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		if (this.cost < o.cost) {
			return -1;
		} else {
			return 1;
		}
	}
}
