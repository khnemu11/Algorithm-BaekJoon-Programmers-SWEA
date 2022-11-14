import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int min;
	static int max;
	static String num;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int size = sc.nextInt();
			int sum = 0;
			int board[][] = new int[size][size];
			int length = 0;
			for (int i = 0; i < size; i++) {
				board[i] = Arrays.stream(sc.next().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();
				for (int curr = size / 2 - length; curr <= size / 2 + length; curr++) {
					sum += board[i][curr];
				}
				if (i < size / 2) {
					length++;
				} else {
					length--;
				}
			}

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(sum);
			System.out.println(result.toString());
		}
	}
}
