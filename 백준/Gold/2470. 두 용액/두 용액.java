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
		int minL = l;
		int minR = r;
		while (l < r) {
			if (min > Math.abs(nums[l] + nums[r])) {
				min = Math.abs(nums[l] + nums[r]);
				minL = l;
				minR = r;
			}

			if (min == 0) {
				break;
			} else if (nums[l] > 0) {
				r = l + 1;
				if (min > Math.abs(nums[l] + nums[r])) {
					min = Math.abs(nums[l] + nums[r]);
					minL = l;
					minR = r;
				}
				break;
			} else if (nums[r] < 0) {
				l = r - 1;
				if (min > Math.abs(nums[l] + nums[r])) {
					min = Math.abs(nums[l] + nums[r]);
					minL = l;
					minR = r;
				}
				break;
			} else if (Math.abs(nums[l + 1] + nums[r]) < Math.abs(nums[l] + nums[r - 1])) {
				l++;
			} else {
				r--;
			}
		}

		System.out.println(nums[minL] + " " + nums[minR]);
	}
}
