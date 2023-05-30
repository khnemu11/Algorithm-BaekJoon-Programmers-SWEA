import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		List<Integer> graph[] = new ArrayList[N + 1];
		int parentNum[] = new int[N + 1];
		int totalTimes[] = new int[N + 1];
		int times[] = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		Queue<Work> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.valueOf(st.nextToken());
			times[i] = time;
			totalTimes[i] = time;
			
			int size = Integer.valueOf(st.nextToken());

			if (size == 0) {
				q.add(new Work(i, 0));
				continue;
			}
			
			for (int j = 0; j < size; j++) {
				int parent = Integer.valueOf(st.nextToken());
				graph[parent].add(i);
				parentNum[i]++;
			}
		}

		while (!q.isEmpty()) {
			Work curr = q.poll();

			for (int child : graph[curr.val]) {
				totalTimes[child] = Math.max(totalTimes[curr.val] + times[child], totalTimes[child]);
				parentNum[child]--;
				if (parentNum[child] == 0) {
					q.add(new Work(child, totalTimes[curr.val]));
				}
			}
		}

		bw.write(Arrays.stream(totalTimes).max().getAsInt() + "\n");
		bw.flush();
	}
}

class Work {
	int val;
	int time;

	public Work(int val, int time) {
		this.val = val;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Work [val=" + val + ", time=" + time + "]";
	}
}