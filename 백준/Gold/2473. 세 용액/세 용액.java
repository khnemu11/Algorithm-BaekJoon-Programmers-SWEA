import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 풀이 과정
 * 
 * 2개의 용액은 이분탐색으로 탐색 가능하므로 1개의 용액을 기준으로 모든 오른쪽의 용액을 이분탐색을 해서 최소값을 구한다
 * 시간 복잡도는 n*logn으로 시간 내에 가능
 * 
 * 
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		int nums[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		Arrays.sort(nums);
		long min = Math.abs((long)nums[0] + nums[1] + nums[2]);
		int result[] = { nums[0], nums[1], nums[2] };
		for (int i = 0; i < nums.length - 2; i++) {
			int start = i;
			int l = start + 1;
			int r = nums.length - 1;

			while (l < r) {
				if (Math.abs((long) nums[start] + nums[l] + nums[r]) < min) {
					min = Math.abs((long) nums[start] + nums[l] + nums[r]);
					result[0] = nums[start];
					result[1] = nums[l];
					result[2] = nums[r];
					if (min == 0) {
						break;
					}
				}

				if ((long) nums[start] + nums[l] + nums[r] > 0) {
					r--;
				} else {
					l++;
				}
			}

			if (min == 0) {
				break;
			}
		}
		bw.write(result[0] + " " + result[1] + " " + result[2] + "\n");
		bw.flush();
	}
}
