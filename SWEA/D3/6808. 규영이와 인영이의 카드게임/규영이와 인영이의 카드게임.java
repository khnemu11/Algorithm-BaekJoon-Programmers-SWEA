import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int win;
	static int lose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean selected[] = new boolean[19];
			int nums[] = new int[9];
			win = 0;
			lose = 0;

			for (int i = 0; i < nums.length; i++) {
				int num = Integer.valueOf(st.nextToken());
				selected[num] = true;
				nums[i] = num;
			}
			pick(0, nums, selected, new int[9]);
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(win).append(" ").append(lose).append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
	}

	public static void pick(int idx, int gyuNum[], boolean selected[], int enNum[]) {
		if (idx == gyuNum.length) {
			int gyuSum = 0, enSum = 0;

			for (int i = 0; i < gyuNum.length; i++) {
				if (gyuNum[i] > enNum[i]) {
					gyuSum += gyuNum[i] + enNum[i];
				} else {
					enSum += gyuNum[i] + enNum[i];
				}
			}
			if (gyuSum > enSum) {
				win++;
			} else {
				lose++;
			}
		} else {
			for (int i = 1; i < selected.length; i++) {
				if (selected[i]) {
					continue;
				}
				selected[i] = true;
				enNum[idx] = i;

				pick(idx + 1, gyuNum, selected, enNum);

				selected[i] = false;
			}
		}
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}