import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int start = Integer.valueOf(st.nextToken());
		int goal = Integer.valueOf(st.nextToken());

		int location[] = new int[100001];
		Arrays.fill(location, -1);
		location[start] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			if (curr == goal) {
				break;
			}
			if (curr * 2 < 100001 && location[curr * 2] == -1) {
				location[curr * 2] = location[curr];
				queue.add(curr * 2);
			}
			if (curr - 1 >= 0 && location[curr - 1] == -1) {
				location[curr - 1] = location[curr] + 1;
				queue.add(curr - 1);
			}
			if (curr + 1 < 100001 && location[curr + 1] == -1) {
				location[curr + 1] = location[curr] + 1;
				queue.add(curr + 1);
			}

		}
		bw.write(String.valueOf(location[goal]));
		bw.newLine();
		br.close();
		bw.close();
	}

}