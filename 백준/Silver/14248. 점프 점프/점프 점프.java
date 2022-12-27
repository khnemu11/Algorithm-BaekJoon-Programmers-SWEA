import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.valueOf(br.readLine());

		int num[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		boolean visited[] = new boolean[size];
		int start = Integer.valueOf(br.readLine()) - 1;
		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		count++;

		while (!queue.isEmpty()) {
			Integer curr = queue.poll();
			int jump = num[curr];
			visited[curr] = true;

			if (curr - jump >= 0 && !visited[curr - jump]) {
				visited[curr - jump] = true;
				queue.add(curr - jump);
				count++;
			}
			if (curr + jump < size && !visited[curr + jump]) {
				visited[curr + jump] = true;
				queue.add(curr + jump);
				count++;
			}
		}
		bw.write(String.valueOf(count) + "\n");
		bw.flush();
	}
}
