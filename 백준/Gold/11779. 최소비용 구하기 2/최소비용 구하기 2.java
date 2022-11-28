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

		int city = Integer.valueOf(br.readLine());
		int bus = Integer.valueOf(br.readLine());

		ArrayList<ArrayList<Vertex>> route = new ArrayList<>();

		for (int i = 0; i <= city; i++) {
			route.add(new ArrayList<Vertex>());
			route.get(i).add(new Vertex(i, 0));
		}
		for (int i = 0; i < bus; i++) {
			int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			route.get(row[0]).add(new Vertex(row[1], row[2]));
		}

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int start = row[0];
		int goal = row[1];
		int INF = 200000000;
		int minRoute[] = new int[city + 1];
		Arrays.fill(minRoute, INF);
		minRoute[start] = 0;
		boolean visited[] = new boolean[city + 1];
		ArrayList<ArrayList<Integer>> path = new ArrayList<>();

		for (int i = 0; i <= city; i++) {
			path.add(new ArrayList<Integer>());
		}

		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(new Vertex(start, 0));
		while (!queue.isEmpty()) {
			Vertex mid = queue.poll();
			if (visited[mid.vertex]) {
				continue;
			}
			visited[mid.vertex] = true;
			for (Vertex end : route.get(mid.vertex)) {
//				System.out.println(start + " -> " + mid + " -> " + end);
				int min = Math.min(minRoute[end.vertex], mid.cost + end.cost);

				if (minRoute[end.vertex] != min) {
					minRoute[end.vertex] = min;
					path.get(end.vertex).clear();
					path.get(end.vertex).addAll(path.get(mid.vertex));
					path.get(end.vertex).add(mid.vertex);
					queue.add(new Vertex(end.vertex, min));
				}
			}
//			System.out.println("mid : " + mid.vertex);
//			System.out.println(Arrays.toString(minRoute));
		}

		bw.write(String.valueOf(minRoute[goal]));
		bw.newLine();

		bw.write(String.valueOf(path.get(goal).size() + 1));
		bw.newLine();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < path.get(goal).size(); i++) {
			sb.append(path.get(goal).get(i)).append(" ");
		}
		sb.append(goal);

		bw.write(sb.toString());
		bw.newLine();

		bw.flush();
	}
}

class Vertex implements Comparable<Vertex> {
	int vertex;
	int cost;

	public Vertex(int vertex, int cost) {
		this.vertex = vertex;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Vertex [vertex=" + vertex + ", cost=" + cost + "]";
	}

	@Override
	public int compareTo(Vertex o) {
		return this.cost - o.cost;
	}
}
