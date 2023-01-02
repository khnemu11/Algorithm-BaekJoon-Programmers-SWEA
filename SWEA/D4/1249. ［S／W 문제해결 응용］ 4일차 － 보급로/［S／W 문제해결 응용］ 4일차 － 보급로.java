import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			boolean visited[][] = new boolean[N][N];
			int cost[][] = new int[N][N];
			int graph[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();
				Arrays.fill(cost[i], 100000000);
			}

			cost[0][0] = 0;
			PriorityQueue<Coordinate> pq = new PriorityQueue<>();
			pq.add(new Coordinate(0, 0));
			Coordinate start = new Coordinate(0, 0);

			while (!pq.isEmpty()) {
				Coordinate mid = pq.poll();
				if (visited[mid.row][mid.col]) {
					continue;
				}
				visited[mid.row][mid.col] = true;

				int upDown[] = { -1, 0, 0, 1 };
				int leftRight[] = { 0, -1, 1, 0 };

				for (int k = 0; k < upDown.length; k++) {
					Coordinate next = new Coordinate(mid.row + upDown[k], mid.col + leftRight[k]);

					if (next.row < 0 || next.col < 0 || next.row >= graph.length || next.col >= graph.length
							|| visited[next.row][next.col]) {
						continue;
					}

					if (cost[next.row][next.col] > cost[mid.row][mid.col] + graph[mid.row][mid.col]) {
						cost[next.row][next.col] = cost[mid.row][mid.col] + graph[mid.row][mid.col];
						next.val = cost[next.row][next.col];
						pq.add(next);
					}
				}

			}

			StringBuilder sb = new StringBuilder();
			sb.append("#" + test_case + " " + cost[N - 1][N - 1]);
			bw.write(sb.toString());
			bw.newLine();
		}

		bw.flush();
	}
}

class Coordinate implements Comparable<Coordinate> {
	int row;
	int col;
	int val;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public int compareTo(Coordinate o) {

		return this.val - o.val;
	}
}