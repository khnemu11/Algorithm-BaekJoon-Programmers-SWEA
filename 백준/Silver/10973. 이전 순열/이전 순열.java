import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

		int N = Integer.valueOf(br.readLine());

		int num[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> (Integer.valueOf(x))).toArray();

		StringBuilder sb = new StringBuilder();

		for (int i = N - 1; i > 0; i--) {
			queue.add(num[i]);
			if (num[i] < num[i - 1]) {
				Queue<Integer> bigQueue = new LinkedList<>();

				while (!queue.isEmpty() && queue.peek() > num[i - 1]) {
					bigQueue.add(queue.poll());
				}

				int temp = queue.poll();
				queue.add(num[i - 1]);
				num[i - 1] = temp;

				int index = 0;

				for (; index < i; index++) {
					sb.append(num[index]);
					sb.append(" ");
				}

				while (!bigQueue.isEmpty()) {
					sb.append(bigQueue.poll());
					sb.append(" ");
				}
				while (!queue.isEmpty()) {
					sb.append(queue.poll());
					sb.append(" ");
				}
				break;
			}
		}
		if (sb.length() == 0) {
			bw.write("-1");
		}
		else {
			bw.write(sb.toString());
		}
		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
	}

}