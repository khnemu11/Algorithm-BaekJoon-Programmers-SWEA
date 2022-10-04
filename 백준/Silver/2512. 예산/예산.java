import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Queue<Integer> queue = new LinkedList<>();

		int size = Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size; i++) {
			queue.add(Integer.valueOf(st.nextToken()));
		}
		int budget = Integer.valueOf(br.readLine());
		int shortageRequest = size;
		int max = 0;
		while (!queue.isEmpty()) {
			int candidate = budget / shortageRequest;
			int satisfied = 0;
			for (int i = 0; i < shortageRequest; i++) {
				int request = queue.poll();

				if (request <= candidate) {
					budget -= request;
					satisfied++;
					if (max < request) {
						max = request;
					}
				} else {
					queue.add(request);
				}
			}

			if (satisfied == 0) {
				max = budget / queue.size();

				break;
			} else {
				shortageRequest = queue.size();
			}
		}
		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
