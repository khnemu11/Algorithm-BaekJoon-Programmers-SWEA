import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int parentNum[];
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

		parentNum = new int[N + 1];
		parents = new int[N + 1];
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			parents[i] = i;
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int length = Integer.valueOf(st.nextToken());

			int parent = Integer.valueOf(st.nextToken());

			for (int i = 0; i < length - 1; i++) {
				int child = Integer.valueOf(st.nextToken());
				graph.get(parent).add(child);
				parentNum[child]++;
				parent = child;
			}
		}

		Queue<Integer> result = new LinkedList<>();
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i < parentNum.length; i++) {
			if (parentNum[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int curr = q.poll();
			result.add(curr);
			for (int child : graph.get(curr)) {
				parentNum[child]--;
				if (parentNum[child] == 0) {
					q.add(child);
				}
			}

		}

		if (result.size() != N) {
			System.out.println("0");
		} else {
			while (!result.isEmpty()) {
				System.out.println(result.poll());
			}
		}
	}
}
