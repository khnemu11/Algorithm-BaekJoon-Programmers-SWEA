import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

		int parentNums[] = new int[N + 1];
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.valueOf(st.nextToken());
			int child = Integer.valueOf(st.nextToken());

			graph.get(parent).add(child);
			parentNums[child]++;
		}

		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i < parentNums.length; i++) {
			if (parentNums[i] == 0) {
				q.add(i);
			}
		}

		StringBuilder resultBuilder = new StringBuilder();

		while (!q.isEmpty()) {
			int parent = q.poll();
			resultBuilder.append(parent + " ");

			for (int child : graph.get(parent)) {
				parentNums[child]--;
				if (parentNums[child] == 0) {
					q.add(child);
				}
			}
		}

		System.out.println(resultBuilder.deleteCharAt(resultBuilder.length() - 1).toString());
	}
}