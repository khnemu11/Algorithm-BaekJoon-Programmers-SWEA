import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int nodeSize = row[0];
		int searchRange = row[1];
		int edgeSize = row[2];

		ArrayList<ArrayList<Node>> graph = new ArrayList<>();

		for (int i = 0; i <= nodeSize; i++) {
			graph.add(new ArrayList<Node>());
		}

		int items[] = new int[nodeSize + 1];
		row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		for (int i = 1; i <= nodeSize; i++) {
			items[i] = row[i - 1];
		}

		for (int i = 0; i < edgeSize; i++) {
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			graph.get(row[0]).add(new Node(row[1], row[2]));
			graph.get(row[1]).add(new Node(row[0], row[2]));
		}

		int cost[][] = new int[nodeSize + 1][nodeSize + 1];

		for (int i = 0; i <= nodeSize; i++) {
			Arrays.fill(cost[i], 10000);
			cost[i][i] = 0;
		}

		for (int start = 0; start <= nodeSize; start++) {
			for (int end = 0; end < graph.get(start).size(); end++) {
				cost[start][graph.get(start).get(end).node] = graph.get(start).get(end).cost;
			}
		}

		for (int mid = 1; mid < cost[0].length; mid++) {
			for (int start = 1; start < cost[0].length; start++) {
				for (int end = 1; end < cost[0].length; end++) {
					if (cost[start][end] > cost[start][mid] + cost[mid][end]) {
						cost[start][end] = cost[start][mid] + cost[mid][end];
					}
				}
			}
		}

		int max = 0;

		for (int i = 1; i <= nodeSize; i++) {
			int sum = 0;

			for (int j = 0; j < cost[0].length; j++) {
				if (cost[i][j] <= searchRange) {
					sum += items[j];
				}
			}

			max = Math.max(sum, max);
		}

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
	}
}

class Node {
	int node;
	int cost;

	public Node(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "[ node : " + node + " cost :  " + cost + " ]";
	}
}