
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T;
		T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			LinkedList<String> list = new LinkedList<>();
			int length = sc.nextInt();

			for (int i = 0; i < length; i++) {
				list.add(sc.next());
			}

			int commandCnt = sc.nextInt();

			while (commandCnt-- > 0) {
				sc.next();
				int index = sc.nextInt();
				int size = sc.nextInt();

				for (int i = 0; i < size; i++) {
					list.add(index + i, sc.next());
				}
			}

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ");

			for (int i = 0; i < 10; i++) {
				result.append(list.get(i)).append(" ");
			}
			result.deleteCharAt(result.length() - 1);

			System.out.println(result.toString());
		}
	}
}
