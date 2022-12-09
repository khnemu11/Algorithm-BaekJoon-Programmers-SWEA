import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int max = 0;
	static boolean visited[];
	static int num[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.valueOf(br.readLine());
		num = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		visited = new boolean[size];

		findMax(0, size, new ArrayList<Integer>());

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
	}

	public static void findMax(int curr, int depth, ArrayList<Integer> candidateList) {
		if (curr == depth) {
			int sum = 0;

			for (int i = 0; i < candidateList.size() - 1; i++) {
				sum += Math.abs(candidateList.get(i) - candidateList.get(i + 1));
			}
			max = Math.max(max, sum);
		} else {
			for (int i = 0; i < num.length; i++) {
				if (visited[i]) {
					continue;
				}

				visited[i] = true;
				candidateList.add(num[i]);
				findMax(curr + 1, depth, candidateList);
				visited[i] = false;
				candidateList.remove(candidateList.size() - 1);
			}
		}
	}
}
