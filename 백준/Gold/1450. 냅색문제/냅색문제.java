import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer> aSum = new ArrayList<>();
	static ArrayList<Integer> bSum = new ArrayList<>();;
	static int nums[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int C = Integer.valueOf(st.nextToken());
		long total = 0;

		nums = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		aPick(0, 0, C);
		bPick(nums.length / 2, 0, C);
		Collections.sort(bSum);

		for (int num : aSum) {
			int l = 0;
			int r = bSum.size();
			int cnt = 0;
			while (l <= r && l < bSum.size()) {
				int mid = (l + r) / 2;
				if (bSum.get(mid) + num <= C) {
					cnt = mid;
					l = mid + 1;
				} else {
					r = mid - 1;
				}
			}
			total += cnt + 1;
		}
		bw.write("" + total);
		bw.newLine();
		bw.flush();
	}

	public static void aPick(int idx, int sum, int target) {
		if (idx >= nums.length / 2) {
			aSum.add(sum);
			return;
		}
		aPick(idx + 1, sum, target);
		if (nums[idx] + sum <= target) {
			aPick(idx + 1, sum + nums[idx], target);
		}
	}

	public static void bPick(int idx, int sum, int target) {
		if (idx >= nums.length) {
			bSum.add(sum);
			return;
		}

		bPick(idx + 1, sum, target);
		if (nums[idx] + sum <= target) {
			bPick(idx + 1, sum + nums[idx], target);
		}
	}
}
