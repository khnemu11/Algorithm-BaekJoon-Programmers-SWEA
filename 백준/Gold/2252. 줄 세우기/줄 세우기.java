import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int nodeSize = input[0];
		int edgeSize = input[1];

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		int nodeParentNum[] = new int[nodeSize + 1];
		for (int i = 0; i <= nodeSize; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < edgeSize; i++) {
			input = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			int start = input[0];
			int end = input[1];

			graph.get(start).add(end);
			nodeParentNum[end]++;
		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i < nodeParentNum.length; i++) {
			if (nodeParentNum[i] == 0) {
				queue.add(i);
			}
		}

		StringBuilder sb = new StringBuilder();

		while (!queue.isEmpty()) {
			int start = queue.poll();
			sb.append(start + " ");

			for (int i = 0; i < graph.get(start).size(); i++) {
				int end = graph.get(start).get(i);
				nodeParentNum[end]--;

				if (nodeParentNum[end] == 0) {
					queue.add(end);
				}
			}
		}

		bw.write(sb.deleteCharAt(sb.length() - 1).toString());
		bw.flush();

		br.close();
		bw.close();
	}
}