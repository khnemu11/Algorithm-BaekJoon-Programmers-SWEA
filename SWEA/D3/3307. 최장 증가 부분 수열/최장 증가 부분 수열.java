import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/* 
	풀이 과정
*/
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int size = Integer.valueOf(br.readLine());
			int result[] = new int[size];
			int nums[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			int length = 1;
			result[0] = nums[0];

			for (int i = 1; i < nums.length; i++) {
				int num = nums[i];
				if (result[length - 1] < num) {
					result[length] = num;
					length++;
				} else {
					int l = 0;
					int r = length - 1;
					int target = 0;
					int mid = (l + r) / 2;
					while (l <= r) {
						mid = (l + r) / 2;

						if (result[mid] < num) {
							target = mid + 1;
							l = mid + 1;
						} else {
							r = mid - 1;
						}
					}
					result[target] = num;
				}
			}

			bw.write("#" + test_case + " " + length + "\n");
		}
		bw.flush();
	}
}
