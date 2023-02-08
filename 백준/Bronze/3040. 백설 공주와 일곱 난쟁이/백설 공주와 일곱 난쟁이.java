import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
	풀이 알고리즘
	9명 중 7명을 선택해서 합이 100이면 dfs를 중단하는 알고리즘 생성
*/

public class Main {
	static int N = 7;
	static int nums[];
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = 9;
		nums = new int[size];
		visited = new boolean[size];

		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.valueOf(br.readLine());
		}

		find(0, 0, 0);

		for (int i = 0; i < nums.length; i++) {
			if (!visited[i]) {
				continue;
			}
			bw.write(String.valueOf(nums[i]) + "\n");
		}

		bw.flush();
	}

	public static boolean find(int curr, int idx, int sum) {
		if (curr == N) {
			if (sum == 100) {
				return true;
			}
			return false;
		} else {
			for (int i = idx; i < nums.length; i++) {
				if (visited[i] || sum + nums[i] > 100) {
					continue;
				}

				visited[i] = true;

				if (find(curr + 1, i + 1, sum + nums[i])) {
					return true;
				}

				visited[i] = false;
			}
			return false;
		}
	}
}
