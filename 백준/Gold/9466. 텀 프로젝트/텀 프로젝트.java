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
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int length = Integer.valueOf(br.readLine());
			int parentNums[] = new int[length + 1];
			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

			for (int i = 0; i <= length; i++) {
				graph.add(new ArrayList<>());
			}
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int from = 1; from <= length; from++) {
				int to = Integer.valueOf(st.nextToken());
				graph.get(from).add(to);
				parentNums[to]++;
			}

			Queue<Integer> q = new LinkedList<>();

			for (int i = 1; i < parentNums.length; i++) {
				if (parentNums[i] == 0) {
					q.add(i);
				}
			}

			int cnt = 0;

			while (!q.isEmpty()) {
				int parent = q.poll();
				cnt++;

				for (int child : graph.get(parent)) {
					parentNums[child]--;

					if (parentNums[child] == 0) {
						q.add(child);
					}
				}
			}

			System.out.println(cnt);
		}
	}
}
