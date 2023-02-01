import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 
 * 풀이 알고리즘
	1) 해쉬로 숫자 -> 개수 저장
	2) 현재가 curr, 대상이 target인 경우 target- curr을 해쉬에서 탐색, 있다면 가능
	3) 같은 숫자라면 개수가 2개 이상이면 가능
*/

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new HashMap<>();
		int nums[] = new int[N];

		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}
		int cnt = 0;
		for (int i = 0; i < nums.length; i++) {
			int target = nums[i];
			map.put(target, map.get(target) - 1);
			boolean isFind = false;
			for (int j = 0; j < nums.length; j++) {
				if (i == j) {
					continue;
				}

				map.put(nums[j], map.get(nums[j]) - 1);

				if (map.get(target - nums[j]) != null && map.get(target - nums[j]) > 0) {
					map.put(nums[j], map.get(nums[j]) + 1);
					isFind = true;
					break;

				} else {
					map.put(nums[j], map.get(nums[j]) + 1);
				}
			}
			if (isFind) {
				cnt++;
			}

			map.put(target, map.get(target) + 1);
		}

		System.out.println(cnt);
	}
}
