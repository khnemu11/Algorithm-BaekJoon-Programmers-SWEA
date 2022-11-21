import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static boolean visited[];
	static ArrayList<ArrayList<Node>> tree;
	static int max;
	static Node end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int V = Integer.valueOf(br.readLine());
		visited = new boolean[V + 1];
		tree = new ArrayList<>();

		for (int i = 0; i <= V; i++) {
			tree.add(new ArrayList<Node>());
		}

		for (int i = 1; i < V; i++) {
			int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			tree.get(row[0]).add(new Node(row[1], row[2]));
			tree.get(row[1]).add(new Node(row[0], row[2]));
		}
		end = new Node(1, 0);
		dfs(1, 0);
		visited = new boolean[V + 1];
		dfs(end.vertex, 0);

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
	}

	public static void dfs(int start, int sum) {
		visited[start] = true;

		for (int i = 0; i < tree.get(start).size(); i++) {
			Node curr = tree.get(start).get(i);
			if (visited[curr.vertex]) {
				continue;
			}

			if (max < sum + curr.edge) {
				max = sum + curr.edge;
				end = curr;
			}

			dfs(curr.vertex, sum + curr.edge);
			visited[curr.vertex] = false;
		}
	}
}

class Node {
	int vertex;
	int edge;

	public Node() {
	}

	public Node(int vertex, int edge) {
		this.vertex = vertex;
		this.edge = edge;
	}

	@Override
	public String toString() {
		return "Node [vertex=" + vertex + ", edge=" + edge + "]";
	}
}
