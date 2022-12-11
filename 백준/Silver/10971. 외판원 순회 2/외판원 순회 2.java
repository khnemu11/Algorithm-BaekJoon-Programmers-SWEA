import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static boolean visited[];
	static int graph[][];
	static long min = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.valueOf(br.readLine());
		visited = new boolean[size];
		graph = new int[size][size];

		for (int i = 0; i < size; i++) {
			graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		}

		visited[0] = true;
		dfs(0, 0, 0);

		bw.write(String.valueOf(min));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}

	public static void dfs(int curr, int count, long sum) {
		if (count == graph.length - 1) {
			if (graph[curr][0] == 0) {
				return;
			} else {
				min = Math.min(sum + graph[curr][0], min);
			}
		} else {
			for (int k = 1; k < graph.length; k++) {
				if (visited[k] || graph[curr][k] == 0) {
					continue;
				}
				visited[k] = true;
				dfs(k, count + 1, sum + graph[curr][k]);
				visited[k] = false;
			}
		}

	}
}
