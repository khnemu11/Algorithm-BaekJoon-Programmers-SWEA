import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int L = Integer.valueOf(st.nextToken());
			Food foods[] = new Food[N];
			boolean visited[] = new boolean[N];
			max = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				foods[i] = new Food(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
			}

			pick(visited, foods, 0, 0, 0, L);

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(max).append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
	}

	public static void pick(boolean visited[], Food foods[], int idx, int scoreSum, int calSum, int L) {
		if (idx == foods.length) {
			max = Math.max(scoreSum, max);
		} else {
			pick(visited, foods, idx + 1, scoreSum, calSum, L);

			if (calSum + foods[idx].calory <= L) {
				visited[idx] = true;
				pick(visited, foods, idx + 1, scoreSum + foods[idx].score, calSum + foods[idx].calory, L);
				visited[idx] = false;
			}
		}
	}
}

class Food {
	int score;
	int calory;

	public Food(int score, int calory) {
		this.score = score;
		this.calory = calory;
	}
}