import java.io.*;
import java.util.*;
/*
	풀이 알고리즘
	단순 dfs -> 2^n * n! -> 시간초과
	
	왼쪽의 무게는 항상 오른쪽 무게보다 무거우거나 같아야함
	만약 왼쪽의 무게가 전체 무게의 절반보다 크거나 같으면 이 후 추를 마음대로 넣어도 왼쪽의 무게가 큼
	-> 왼쪽의 무게가 전체의 무게의 절반보다 크거나 같으면 해당 위치에서 2^(남은 추의 개수) * (남은 추의 개수)!를 dfs대신 계산
*/

class Solution {
	static int factorial[];
	static boolean visited[];
	static int nums[];
	static long count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.valueOf(br.readLine());
		factorial = new int[10];

		for (int testcase = 1; testcase <= TC; testcase++) {
			int N = Integer.parseInt(br.readLine());
			nums = new int[N];
			visited = new boolean[N];
			count = 0;
			int sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
				sum += nums[i];
			}

			int half = sum % 2 == 0 ? sum / 2 : sum / 2 + 1;
			pick(0, half, 0, 0);

			System.out.println("#" + testcase + " " + count);
		}
		br.close();
	}

	public static void pick(int cnt, int halfWeight, int left, int right) { // 무게 추를 고르는 메소드
		if (cnt == nums.length) {	//끝까지 고른 케이스
			count++;
		} else if (left >= halfWeight) { //왼쪽의 무게가 전체 무게의 절반에 다다르거나 넘었을 경우
			int rest = nums.length - cnt; // 아직 고르지 않은 숫자
			count = count + (long) Math.pow(2, rest) * factorial(rest);
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (visited[i]) {
					continue;
				}

				visited[i] = true;

				pick(cnt + 1, halfWeight, left + nums[i], right);

				if (left >= right + nums[i]) {	//왼쪽의 무게가 오른쪽 무게에 추를 올렸을때 보다 큰 경우
					pick(cnt + 1, halfWeight, left, right + nums[i]);
				}

				visited[i] = false;
			}
		}
	}

	public static int factorial(int num) { // 펙토리얼을 구하는 메소드 (n)
		if (factorial[num] > 0) {
			return factorial[num];
		} else if (num == 1) {
			factorial[num] = 1;
			return factorial[num];
		} else {
			factorial[num] = factorial(num - 1) * num;
			return factorial[num];
		}
	}
}