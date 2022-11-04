import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int F = Integer.valueOf(st.nextToken());
		int S = Integer.valueOf(st.nextToken());
		int G = Integer.valueOf(st.nextToken());
		int U = Integer.valueOf(st.nextToken());
		int D = Integer.valueOf(st.nextToken());

		boolean visited[] = new boolean[F + 1];
		PriorityQueue<Count> queue = new PriorityQueue<>();
		queue.add(new Count(S, 0));
		int result = -1;

		while (!queue.isEmpty()) {
			Count curr = queue.poll();

			if (visited[curr.location]) {
				continue;
			}
			
			visited[curr.location] = true;
			
			if (curr.location == G) {
				result = curr.count;
				break;
			}
			if (U > 0 && curr.location + U <= F && !visited[curr.location + U]) {
				queue.add(new Count(curr.location + U, curr.count + 1));
			}
			if (D > 0 && curr.location - D > 0 && !visited[curr.location - D]) {
				queue.add(new Count(curr.location - D, curr.count + 1));
			}

		}

		if (result == -1) {
			bw.write("use the stairs");
		} else {
			bw.write(String.valueOf(result));
		}

		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}

class Count implements Comparable<Count> {
	int location;
	int count;

	public Count(int location, int count) {
		this.location = location;
		this.count = count;
	}

	@Override
	public int compareTo(Count o) {
		return this.count - o.count;
	}

	@Override
	public String toString() {
		return "Count [location=" + location + ", count=" + count + "]";
	}
}