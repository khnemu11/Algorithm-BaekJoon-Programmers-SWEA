import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.valueOf(st.nextToken());
		int E = Integer.valueOf(st.nextToken());
		int inf = Integer.MAX_VALUE;
		ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<Vertex>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());

			graph.get(from).add(new Vertex(to, cost));
		}

		long distance[] = new long[V + 1];
		Arrays.fill(distance, inf);
		distance[1] = 0;

		for (int i = 0; i < V - 1; i++) {
			for (int mid = 1; mid <= V; mid++) {
				if (distance[mid] == inf) {
					continue;
				}

				for (Vertex curr : graph.get(mid)) {
					distance[curr.v] = Math.min(distance[curr.v], distance[mid] + curr.cost);
				}
			}
		}

		boolean hasCycle = false;

		for (int mid = 1; mid <= V; mid++) {
			if (distance[mid] == inf) {
				continue;
			}
			
			for (Vertex curr : graph.get(mid)) {
				if (distance[curr.v] > distance[mid] + curr.cost) {
					hasCycle = true;
					break;
				}
			}
			if (hasCycle) {
				break;
			}
		}

		if (hasCycle) {
			bw.write("-1");
			bw.newLine();
		} else {
			for (int i = 2; i < distance.length; i++) {
				if (distance[i] == inf) {
					bw.write("-1");
				} else {
					bw.write(String.valueOf(distance[i]));
				}

				bw.newLine();
			}
		}
		bw.flush();
	}
}

class Vertex {
	int v;
	int cost;

	public Vertex(int v, int cost) {
		this.v = v;
		this.cost = cost;
	}
}
