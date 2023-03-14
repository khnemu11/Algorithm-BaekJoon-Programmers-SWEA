import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
	풀이 알고리즘
	
	정렬 후 용액 2개를 골라 투포인터 탐색
	n*n = O(n^2)
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		long nums[] = Arrays.stream(br.readLine().split(" ")).mapToLong(x -> Long.valueOf(x)).toArray();

		Arrays.sort(nums);

		long min = Long.MAX_VALUE;
		long result[] = { nums[0], nums[1], nums[2] };

		for (int i = 0; i < nums.length; i++) {
			long num = nums[i];

			int l = i + 1;
			int r = nums.length - 1;

			while (l < r) {
				long sum = num + nums[l] + nums[r];
				if (Math.abs(sum) < Math.abs(min)) {
					min = sum;
					result[0] = num;
					result[1] = nums[l];
					result[2] = nums[r];
				}
				if (sum < 0) {
					l++;
				} else {
					r--;
				}
			}
		}

		bw.write(result[0] + " " + result[1] + " " + result[2] + "\n");
		bw.flush();
	}
}