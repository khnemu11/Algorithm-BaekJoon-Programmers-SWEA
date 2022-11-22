import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long start = Integer.valueOf(st.nextToken());
		long goal = Integer.valueOf(st.nextToken());

		Queue<Long> queue = new LinkedList<>();
		queue.add(start);

		int count = 0;
		boolean isValid = false;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int k = 0; k < size; k++) {
				long curr = queue.poll();
				// 현재값 = goal
				if (curr == goal) {
					isValid = true;
					break;
				}
				// 1붙이기
				long next = curr * 10 + 1;
				if (next <= goal) {
					queue.add(next);
				}

				// 2곱하기

				next = curr * 2;

				if (next <= goal) {
					queue.add(next);
				}
			}
			count++;
			if (isValid) {
				break;
			}
		}

		if (isValid) {
			bw.write(String.valueOf(count));
		} else {
			bw.write("-1");
		}
		bw.newLine();
		bw.flush();
	}

}
