import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int V = Integer.valueOf(st.nextToken());
			int E = Integer.valueOf(st.nextToken());
			int W = Integer.valueOf(st.nextToken());

			int node[][] = new int[V + 1][V + 1];

			for (int i = 0; i <= V; i++) {
				Arrays.fill(node[i], Integer.MAX_VALUE);
				node[i][i] = 0;
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int cost = Integer.valueOf(st.nextToken());

				node[start][end] = Math.min(node[start][end], cost);
				node[end][start] = Math.min(node[end][start], cost);
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int cost = Integer.valueOf(st.nextToken());

				node[start][end] = Math.min(node[start][end], cost * -1);
			}

			for (int mid = 1; mid <= V; mid++) {
				for (int start = 1; start <= V; start++) {
					for (int end = 1; end <= V; end++) {
						if (node[start][mid] == Integer.MAX_VALUE || node[mid][end] == Integer.MAX_VALUE) {
							continue;
						}
						node[start][end] = Math.min(node[start][end], node[start][mid] + node[mid][end]);
					}
				}
			}

			boolean isValid = false;

			for (int i = 1; i <= V; i++) {
				if (node[i][i] < 0) {
					isValid = true;
					break;
				}
			}

			if (isValid) {
				bw.write("YES");
			} else {
				bw.write("NO");
			}
			bw.newLine();
		}

		bw.flush();
	}

}
