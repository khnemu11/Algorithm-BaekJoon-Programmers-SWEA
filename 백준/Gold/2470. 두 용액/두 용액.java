import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		int nums[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		Arrays.sort(nums);

		int l = 0;
		int r = size - 1;
		int min = Integer.MAX_VALUE;
		int minL = nums[l];
		int minR = nums[r];

		while (l < r) {

			if (min > Math.abs(nums[l] + nums[r])) {
				minL = nums[l];
				minR = nums[r];
				min = Math.abs(nums[l] + nums[r]);
			}

			if (nums[l] + nums[r] > 0) {
				r--;
			} else {
				l++;
			}
		}

		System.out.println(minL + " " + minR);
	}
}
