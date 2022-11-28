import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int count = 0;
	static int loop = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int start = row[0];
		int goal = row[1];
		int time[] = new int[100001];

		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		int loop = 0;
		int count = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean find = false;
			while (size-- > 0) {
				int curr = queue.poll();
				if (curr == goal) {
					find = true;
					count++;
					continue;
				} else if (time[curr] != loop && time[curr] > 0) {
					continue;
				}
				time[curr] = loop;

				if (curr - 1 >= 0) {
					queue.add(curr - 1);
				}
				if (curr + 1 <= 100000) {
					queue.add(curr + 1);
				}
				if (curr * 2 <= 100000) {
					queue.add(curr * 2);
				}
			}

			if (find) {
				bw.write(String.valueOf(loop));
				bw.newLine();
				bw.write(String.valueOf(count));
				bw.newLine();

				break;
			}

			loop++;
		}

		bw.flush();
	}
}
