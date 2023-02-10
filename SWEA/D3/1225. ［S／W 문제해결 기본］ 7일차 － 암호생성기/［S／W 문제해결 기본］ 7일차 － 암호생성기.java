import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			int nums[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			Queue<Integer> q = new LinkedList<>();

			for (int num : nums) {
				q.add(num);
			}
			boolean exit = false;
			
			while (!exit) {
				int loop = 5;
				int weight = 1;

				while (loop-- > 0) {
					int curr = q.poll();
					if (curr - weight <= 0) {
						q.add(0);
						exit = true;	
						break;
					} else {
						q.add(curr - weight);
					}
					weight++;
				}
			}

			StringBuilder sb = new StringBuilder();

			sb.append("#").append(test_case).append(" ");

			while (!q.isEmpty()) {
				sb.append(q.poll()).append(" ");
			}
			sb.deleteCharAt(sb.length() - 1).append("\n");

			bw.write(sb.toString());
		}

		bw.flush();
	}
}