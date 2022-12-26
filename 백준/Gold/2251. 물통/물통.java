import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[][][];
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.valueOf(st.nextToken());
		int b = Integer.valueOf(st.nextToken());
		int c = Integer.valueOf(st.nextToken());

		visited = new boolean[a + 1][b + 1][c + 1];

		pour(0, 0, c, a, b, c);

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			sb.append(pq.poll() + " ");
		}
		bw.write(sb.deleteCharAt(sb.length() - 1).toString());
		bw.newLine();
		bw.flush();
	}

	public static void pour(int a, int b, int c, int maxA, int maxB, int maxC) {

		if (visited[a][b][c]) {
			return;
		}

		visited[a][b][c] = true;

		if (a == 0) {
			pq.add(c);
		}

		if (a + b > maxB) {
			pour(a + b - maxB, maxB, c, maxA, maxB, maxC);
		} else {
			pour(0, a + b, c, maxA, maxB, maxC);
		}
		if (a + c > maxC) {
			pour(a + c - maxC, b, maxC, maxA, maxB, maxC);
		} else {
			pour(0, b, a + c, maxA, maxB, maxC);
		}

		if (b + a > maxA) {
			pour(maxA, a + b - maxA, c, maxA, maxB, maxC);
		} else {
			pour(a + b, 0, c, maxA, maxB, maxC);
		}
		if (b + c > maxC) {
			pour(a, b + c - maxC, maxC, maxA, maxB, maxC);
		} else {
			pour(a, 0, b + c, maxA, maxB, maxC);
		}

		if (c + a > maxA) {
			pour(maxA, b, a + c - maxA, maxA, maxB, maxC);
		} else {
			pour(a + c, b, 0, maxA, maxB, maxC);
		}
		if (c + b > maxB) {
			pour(a, maxB, b + c - maxB, maxA, maxB, maxC);
		} else {
			pour(a, b + c, 0, maxA, maxB, maxC);
		}
	}
}
