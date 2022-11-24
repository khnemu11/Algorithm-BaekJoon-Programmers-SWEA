import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int V = row[0];
		int W = row[1];
		int X = row[2];

		int road[][] = new int[V + 1][V + 1];
		int cost[][] = new int[V + 1][V + 1];
		for (int i = 0; i <= V; i++) {
			Arrays.fill(road[i], Integer.MAX_VALUE);
			Arrays.fill(cost[i], Integer.MAX_VALUE);
			road[i][i] = 0;
		}

		for (int i = 0; i < W; i++) {
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			int start = row[0];
			int end = row[1];
			int distance = row[2];

			road[start][end] = distance;
		}

		for (int mid = 1; mid <= V; mid++) {
			for (int start = 1; start <= V; start++) {
				for (int end = 1; end <= V; end++) {
					if (road[start][mid] == Integer.MAX_VALUE || road[mid][end] == Integer.MAX_VALUE) {
						continue;
					}
					road[start][end] = Math.min(road[start][end], road[start][mid] + road[mid][end]);
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= V; i++) {
			max = Math.max(max, road[i][X] + road[X][i]);
		}
		bw.write(String.valueOf(max));
		bw.flush();
	}
}
