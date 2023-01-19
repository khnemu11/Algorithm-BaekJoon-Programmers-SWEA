import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static int parents[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int N = row[0];
		int M = row[1];
		parents = new int[N + 1];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		PriorityQueue<Path> pq = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			pq.add(new Path(row[0], row[1], row[2]));
		}
		int cnt = 0;
		int max = 0;
		long sum = 0;
		while (!pq.isEmpty() && cnt < N - 1) {
			Path curr = pq.poll();

			if (getParent(curr.start) == getParent(curr.end)) {
				continue;
			}
			union(curr.start, curr.end);
			max = Math.max(max, curr.cost);
			sum += curr.cost;
		}

		sum -= max;
		System.out.println(sum);
	}

	public static int getParent(int now) {
		if (now == parents[now]) {
			return now;
		} else {
			parents[now] = getParent(parents[now]);
			return parents[now];
		}
	}

	public static void union(int parent, int child) {
		int a = getParent(parent);
		int b = getParent(child);

		parents[b] = a;
	}

}

class Path implements Comparable<Path> {
	int start;
	int end;
	int cost;

	public Path(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Path o) {
		return this.cost - o.cost;
	}

	@Override
	public String toString() {
		return "Path [start=" + start + ", end=" + end + ", cost=" + cost + "]";
	}
}
