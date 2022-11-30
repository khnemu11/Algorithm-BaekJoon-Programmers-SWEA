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

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int start = row[0];
		int end = row[1];

		boolean graph[][] = new boolean[size + 1][size + 1];
		boolean visited[] = new boolean[size + 1];
		int edgeNum = Integer.valueOf(br.readLine());

		while (edgeNum-- > 0) {
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			graph[row[0]][row[1]] = true;
			graph[row[1]][row[0]] = true;
		}

		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		int count = 1;
		boolean isFind = false;
		while (!queue.isEmpty()) {
			int loop = queue.size();

			while (loop-- > 0) {
				int curr = queue.poll();
				visited[curr] = true;
				for (int i = 0; i < graph[0].length; i++) {
					if (visited[i] || !graph[curr][i]) {
						continue;
					}
					queue.add(i);
					if (i == end) {
						isFind = true;
						break;
					}
				}
			}
			if (isFind) {
				break;
			}
			count++;
		}

		if (isFind) {
			bw.write(String.valueOf(count));
		} else {
			bw.write("-1");
		}

		bw.newLine();
		bw.flush();
		br.close();
	}

}
