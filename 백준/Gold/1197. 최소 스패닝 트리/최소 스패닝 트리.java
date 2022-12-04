import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int V = row[0];
		int E = row[1];
		boolean visited[] = new boolean[V + 1];

		ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		Vertex min = new Vertex(1, Integer.MAX_VALUE);

		for (int i = 0; i < E; i++) {
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			graph.get(row[0]).add(new Vertex(row[1], row[2]));
			graph.get(row[1]).add(new Vertex(row[0], row[2]));
			if (min.cost > row[2]) {
				min = new Vertex(row[0], row[2]);
			}
		}

		PriorityQueue<Vertex> queue = new PriorityQueue<>();

		for (int i = 0; i < graph.get(min.no).size(); i++) {
			queue.offer(graph.get(min.no).get(i));
		}
		visited[min.no] = true;
		int weight = 0;

		while (!queue.isEmpty()) {
			Vertex curr = queue.poll();
			if (visited[curr.no]) {
				continue;
			}
			visited[curr.no] = true;
			weight += curr.cost;

			for (int i = 0; i < graph.get(curr.no).size(); i++) {
				Vertex next = graph.get(curr.no).get(i);

				if (visited[next.no]) {
					continue;
				}

				queue.offer(next);
			}
		}
		bw.write(String.valueOf(weight));
		bw.newLine();
		bw.flush();
	}
}

class Vertex implements Comparable<Vertex> {
	int no;
	int cost;

	public Vertex(int no, int cost) {
		this.no = no;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Vertex [no=" + no + ", cost=" + cost + "]";
	}

	@Override
	public int compareTo(Vertex o) {
		return this.cost - o.cost;
	}
}
