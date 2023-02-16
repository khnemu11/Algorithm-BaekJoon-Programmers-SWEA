import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
	풀이 알고리즘
	주어진 숫자 중 6개를 고르면 되는 완전탐색 문제
*/

public class Main {
	static int nums[];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int k = 6;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int size = Integer.valueOf(st.nextToken());

			if (size == 0) {
				break;
			}

			nums = new int[size];

			for (int i = 0; i < nums.length; i++) {
				nums[i] = Integer.valueOf(st.nextToken());
			}
			pick(0, new ArrayList<Integer>(), 0);
			bw.write("\n");
		}

		bw.flush();
	}

	public static void pick(int idx, ArrayList<Integer> selected, int cnt) throws IOException {
		if (cnt == k) {
			StringBuilder sb = new StringBuilder();

			for (int num : selected) {
				sb.append(num).append(" ");
			}

			bw.write(sb.deleteCharAt(sb.length() - 1).toString() + "\n");

			return;
		} else {
			for (int i = idx; i < nums.length; i++) {
				selected.add(nums[i]);
				pick(i + 1, selected, cnt + 1);
				selected.remove(selected.size() - 1);
			}
		}
	}
}