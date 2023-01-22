import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		int parents[] = new int[N + 1];
		int init_times[] = new int[N + 1];
		int build_times[] = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			init_times[i] = Integer.valueOf(st.nextToken());
			while (st.hasMoreTokens()) {
				int parent = Integer.valueOf(st.nextToken());
				if (parent == -1) {
					break;
				}

				parents[i]++;
				graph.get(parent).add(i);
			}

			if (parents[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int curr = q.poll();
			build_times[curr] += init_times[curr];

			for (int child : graph.get(curr)) {
				parents[child]--;
				build_times[child] = Math.max(build_times[child], build_times[curr]);

				if (parents[child] == 0) {
					q.add(child);
				}
			}
		}
		
		for (int i = 1; i < build_times.length; i++) {
			bw.write(String.valueOf(build_times[i]));
			bw.newLine();
		}

		bw.flush();
	}
}
