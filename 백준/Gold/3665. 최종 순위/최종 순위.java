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
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.valueOf(br.readLine());
			int parentNum[] = new int[N + 1];
			boolean graph[][] = new boolean[N + 1][N + 1];
			int nums[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			for (int parent = 0; parent < nums.length; parent++) {
				for (int child = parent + 1; child < nums.length; child++) {
					graph[nums[parent]][nums[child]] = true;
					parentNum[nums[child]]++;
				}
			}

			int M = Integer.valueOf(br.readLine());

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.valueOf(st.nextToken());
				int b = Integer.valueOf(st.nextToken());

				if (graph[a][b]) {
					graph[a][b] = false;
					graph[b][a] = true;

					parentNum[b]--;
					parentNum[a]++;
				} else {
					graph[b][a] = false;
					graph[a][b] = true;

					parentNum[a]--;
					parentNum[b]++;
				}
			}

			Queue<Integer> q = new LinkedList<>();

			for (int i = 1; i < parentNum.length; i++) {
				if (parentNum[i] == 0) {
					q.add(i);
				}
			}
			int visitedNum = 0;
			StringBuilder sb = new StringBuilder();
			while (!q.isEmpty()) {
				if (q.size() > 1) {
					break;
				}

				int curr = q.poll();
				visitedNum++;
				sb.append(curr + " ");

				for (int i = 1; i < graph[0].length; i++) {
					if (graph[curr][i] == true) {
						parentNum[i]--;

						if (parentNum[i] == 0) {
							q.add(i);
						}
					}
				}
			}

			if (visitedNum == N) {
				bw.write(sb.deleteCharAt(sb.length() - 1).toString() + "\n");
			} else {
				bw.write("IMPOSSIBLE\n");
			}
		}

		bw.flush(); // 결과 출력
		bw.close();
	}

}