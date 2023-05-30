import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.valueOf(st.nextToken());
			int d = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());

			List<Computer> graph[] = new List[n + 1];

			for (int i = 0; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());

				int child = Integer.valueOf(st.nextToken());
				int parent = Integer.valueOf(st.nextToken());
				int time = Integer.valueOf(st.nextToken());

				graph[parent].add(new Computer(child, time));
			}

			int times[] = new int[n + 1];
			int INF = 1_000 * 10_000 + 1;
			Arrays.fill(times, INF);
			times[c] = 0;

			PriorityQueue<Computer> pq = new PriorityQueue<>();
			pq.add(new Computer(c, 0));

			while (!pq.isEmpty()) {
				Computer curr = pq.poll();
//				System.out.println(curr);
				if (times[curr.val] < curr.time) {
					continue;
				}

				for (Computer end : graph[curr.val]) {
					if (times[end.val] > times[curr.val] + end.time) {
						times[end.val] = times[curr.val] + end.time;

						pq.add(new Computer(end.val, times[end.val]));
					}
				}
			}
			
			int max = 0;
			int cnt = 0;
			
			for (int i = 1; i < times.length; i++) {
				if (times[i] < INF) {
					cnt++;
					max = Math.max(times[i], max);
				}
			}

			bw.write(cnt + " " + max+"\n");
			bw.flush();
		}

		bw.flush();
	}
}

class Computer implements Comparable<Computer> {
	int val;
	int time;

	public Computer(int val, int time) {
		this.val = val;
		this.time = time;
	}

	public int compareTo(Computer computer) {
		return this.time - computer.time;
	}

	@Override
	public String toString() {
		return "Computer [val=" + val + ", time=" + time + "]";
	}
	
}