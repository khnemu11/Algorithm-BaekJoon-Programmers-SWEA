import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int vertex = Integer.valueOf(st.nextToken());
		int edge = Integer.valueOf(st.nextToken());
		int start = Integer.valueOf(br.readLine());
		ArrayList<ArrayList<Vertex>> graph = new ArrayList<ArrayList<Vertex>>();
		for (int i = 0; i < vertex; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			Vertex curr = new Vertex(to - 1, weight);

			graph.get(from - 1).add(curr);
		}
//		System.out.println(graph.toString());
		int cost[] = new int[vertex];
		for (int i = 0; i < vertex; i++) {
			if (i == start - 1) {
				continue;
			}
			cost[i] = Integer.MAX_VALUE;
		}

		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(new Vertex(start - 1, 0));

		while (!queue.isEmpty()) {
			Vertex curr = queue.poll();
//		 	System.out.println(curr + curr.toString());
			for (int j = 0; j < graph.get(curr.index).size(); j++) {
				long currCost = cost[curr.index] + (long) graph.get(curr.index).get(j).cost;
				int index = graph.get(curr.index).get(j).index;
//				System.out.println(cost[index] + " vs curr : " + currCost);
				if ((long) cost[index] > currCost) {
					cost[index] = (int) currCost;
					queue.add(new Vertex(index, cost[index]));
				}
			}
//			System.out.println(queue.toString());
//			System.out.println(Arrays.toString(cost));
		}

		for (int i = 0; i < vertex; i++) {
			if (cost[i] == Integer.MAX_VALUE) {
				bw.write("INF");
			} else {
				bw.write(String.valueOf(cost[i]));
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}

class Vertex implements Comparable<Vertex> {
	int index;
	int cost;

	public Vertex(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}

	@Override
	public int compareTo(Vertex o) {
		return this.cost - o.cost;
	}

	@Override
	public String toString() {
		return "Vertex [index=" + index + ", cost=" + cost + "]";
	}

}
