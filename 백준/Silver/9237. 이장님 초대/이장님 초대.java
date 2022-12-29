import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		List<Integer> list = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < size; i++) {
			pq.add(Integer.valueOf(st.nextToken()) + 1);
		}

		int rest = pq.poll() - 1;
		int curr = 1;

		while (!pq.isEmpty()) {
			int next = pq.poll();
			if (rest <= next) {
				rest = next;
			}
			rest--;
			curr++;

		}
		curr += rest;
		bw.write(String.valueOf(curr + 1) + "\n");
		bw.flush();
	}
}
