import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.valueOf(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			pq.add(Integer.valueOf(br.readLine()));
		}

		int sum = 0;

		while (pq.size() > 1) {
			int card1 = pq.poll();
			int card2 = pq.poll();
			int cards = card1 + card2;
			sum = sum + cards;
			pq.add(cards);
		}
		bw.write(sum + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}