import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = 1;
		while (true) {
			int N = Integer.valueOf(br.readLine());

			if (N == 0) {
				break;
			}

			min = Integer.MAX_VALUE;
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.valueOf(st.nextToken());
				}
			}

			int min = findMin();

			StringBuilder sb = new StringBuilder();
			sb.append("Problem ").append(testcase++).append(": ").append(min).append("\n");
			bw.write(sb.toString());
		}

		bw.flush();
		bw.close();
	}

	public static int findMin() {
		int distance[][] = new int[map.length][map[0].length];
		boolean visited[][] = new boolean[map.length][map[0].length];

		for (int i = 0; i < distance.length; i++) {
			Arrays.fill(distance[i], 200_000);
		}
		distance[0][0] = map[0][0];

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, map[0][0]));

		while (!pq.isEmpty()) {
			Node from = pq.poll();
			if (visited[from.row][from.col]) {
				continue;
			}

			visited[from.row][from.col] = true;

			for (int k = 0; k < upDown.length; k++) {
				Node to = new Node(from.row + upDown[k], from.col + leftRight[k]);

				if (to.row < 0 || to.col < 0 || to.row >= map.length || to.col >= map[0].length
						|| visited[to.row][to.col] || distance[to.row][to.col] < map[to.row][to.col] + from.cost) {
					continue;
				}

				distance[to.row][to.col] = map[to.row][to.col] + from.cost;
				to.cost = distance[to.row][to.col];
				pq.add(to);
			}
		}

		return distance[map.length - 1][map[0].length - 1];
	}
}

class Node implements Comparable<Node> {
	int row;
	int col;
	int cost;

	public Node(int row, int col, int cost) {
		this.row = row;
		this.col = col;
		this.cost = cost;
	}

	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}