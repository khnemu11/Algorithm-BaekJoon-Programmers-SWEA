
import java.io.IOException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; test_case++) {
			int num = sc.nextInt();
			String find = sc.next();
			String target = sc.next();
			int count = 0;

			for (int i = 0; i <= target.length() - find.length(); i++) {
				if (target.substring(i, i + find.length()).equals(find)) {
					count++;
				}
			}

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(count);
			System.out.println(result.toString());
		}
	}
}
