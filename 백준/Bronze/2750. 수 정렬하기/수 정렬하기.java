import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.valueOf(br.readLine());
		}
		insertSort();
		for(int i=0;i<n;i++) {
			System.out.println(nums[i]);
		}
		br.close();
	}

	public static void insertSort() {
		for (int i = 1; i < nums.length; i++) {
			int curr = nums[i];
			int temp = i;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < curr) {
					break;
				} else {
					nums[j + 1] = nums[j];
					temp=j;
				}
			}
			if (temp != i)
				nums[temp] = curr;
		}
	}
}