import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		int parent[] = new int[N + 1];

		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
			parent[i] = i;
		}
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());

			graph.get(start).add(end);
			graph.get(end).add(start);
		}

		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				int curr = queue.poll();

				for (int i = 0; i < graph.get(curr).size(); i++) {
					int next = graph.get(curr).get(i);
					if (parent[next] != next) {
						continue;
					}

					parent[next] = curr;
					queue.add(next);
				}
			}
		}

		for (int i = 2; i <= N; i++) {
			bw.write(String.valueOf(parent[i]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}