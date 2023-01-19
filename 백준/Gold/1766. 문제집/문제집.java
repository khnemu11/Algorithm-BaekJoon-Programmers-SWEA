import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int N = row[0];
		int M = row[1];

		int parents[] = new int[N + 1];
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			parents[row[1]]++;
			graph.get(row[0]).add(row[1]);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 1; i < parents.length; i++) {
			if (parents[i] == 0) {
				pq.add(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			int problem = pq.poll();
			sb.append(problem + " ");
			for (int child : graph.get(problem)) {
				parents[child]--;
				if (parents[child] == 0) {
					pq.add(child);
				}
			}
		}

		System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
	}
}
