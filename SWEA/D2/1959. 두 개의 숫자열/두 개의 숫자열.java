import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = Integer.valueOf(sc.nextLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sc.nextLine();
			int a[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			int b[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			int max = findMax(a.length < b.length ? a : b, a.length < b.length ? b : a);

			StringBuilder sb = new StringBuilder();
			sb.append("#" + test_case + " " + max);
			System.out.println(sb.toString());
		}
	}

	public static int findMax(int shortArr[], int longArr[]) {
		int max = 0;
		for (int start = 0; start + shortArr.length <= longArr.length; start++) {
			int sum = 0;
			for (int curr = 0; curr < shortArr.length; curr++) {
				sum = sum + shortArr[curr] * longArr[start + curr];
			}
			max = Math.max(sum, max);
		}

		return max;
	}

}
