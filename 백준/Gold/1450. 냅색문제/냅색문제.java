import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


/*
	풀이 알고리즘
	1) 2^30은 약 10억 -> dfs 불가
	2) 가방의 최대값은 1억 -> dp를 이용했을 때 최대 배열 크기 = 30*1억 = 30억 -> dp불가
	3) 절반인 2^15는 약 3만으로 dfs 2번이면 6만 연산으로 가능
	4) 절반 한쪽의 값 + 나머지 절반 한쪽의 값을 순차적으로 비교하여 가방의 크기를 넘을때 까지 탐색 -> 2^15 * 2^15 = 2^30 -> 연산 불가
	5) 절반 한쪽의 값 + 나머지 절반 한쪽의 값을 이분탐색으로 비교하여 가방의 크기를 넘을때 까지 탐색 -> 2^15 * log2(2^15) = 2^15*15 ->연산 가능
	6) 각 입력값 숫자를 절반으로 나눠 dfs로 나올 수 있는 모든 합 구하기
	7) 한쪽 배열을 기준으로 이분탐색으로 나머지 배열에서 합칠 수 있는 가장 큰 값을 구하고 해당 값 이하의 개수를 더해줘서 출력
	
	풀이 도출 팁 : 입력값의 크기가 크다면 절반으로 나눠 볼 생각을 하자
*/

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
			int r = bSum.size() - 1;
			int cnt = 0;
			while (l <= r) {
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
