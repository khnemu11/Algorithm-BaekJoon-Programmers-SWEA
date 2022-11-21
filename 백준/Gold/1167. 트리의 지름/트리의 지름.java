import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static boolean visited[];
	static ArrayList<ArrayList<Vertex>> tree;
	static long max = 0;
	static int start = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int V = Integer.valueOf(br.readLine());

		tree = new ArrayList<>();
		visited = new boolean[V + 1];

		for (int i = 0; i <= V; i++) {
			tree.add(new ArrayList<Vertex>());
		}

		for (int i = 0; i < V; i++) {
			int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			int vertex = row[0];

			for (int j = 1; j < row.length - 1; j = j + 2) {
				Vertex curr = new Vertex(row[j], row[j + 1]);
				tree.get(vertex).add(curr);
			}
		}

		findMax(1, 0);
		visited = new boolean[V + 1];
		findMax(start, 0);

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
	}

	public static void findMax(int curr, long sum) {
//		System.out.println("max of curr : " + sum);
		visited[curr] = true;
		for (int i = 0; i < tree.get(curr).size(); i++) {
			Vertex vertex = tree.get(curr).get(i);
//			System.out.println(curr + " -> " + vertex.toString());
			if (visited[vertex.node]) {
				continue;
			}

			long currSum = sum + vertex.edge;

			if (max < currSum) {
				max = currSum;
				start = vertex.node;
			}
			findMax(vertex.node, currSum);
		}

	}
}

class Vertex {
	int node;
	long edge;

	public Vertex(int node, long edge) {
		this.node = node;
		this.edge = edge;
	}

	@Override
	public String toString() {
		return "Vertex [node=" + node + ", edge=" + edge + "]";
	}
}