import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		int daSomVote = Integer.valueOf(br.readLine());
		int count = 0;

		for (int i = 0; i < T - 1; i++) {
			queue.add(Integer.valueOf(br.readLine()));
		}
		if (!queue.isEmpty()) {
			while (daSomVote <= queue.peek()) {
				int candidateVote = queue.poll();
				daSomVote++;
				candidateVote--;
				count++;
				queue.add(candidateVote);
			}
		}

		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
}
