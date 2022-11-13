import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);
		int test_case = 10;
		while (test_case-- > 0) {
			int no = sc.nextInt();

			Queue<Integer> queue = new LinkedList<>();

			int size = 8;

			while (size-- > 0) {
				queue.add(sc.nextInt());
			}
			int decrease = 1;
			while (true) {
				int num = queue.poll();

				if (num - decrease > 0) {
					num -= decrease;
				} else {
					num = 0;
				}

				queue.add(num);

				if (num == 0) {
					break;
				}
				decrease++;
				if (decrease > 5) {
					decrease = 1;
				}
			}
			StringBuilder result = new StringBuilder("#");
			result.append(no).append(" ");
			while (!queue.isEmpty()) {
				result.append(queue.poll());
				result.append(" ");
			}
			result.deleteCharAt(result.length() - 1);
			System.out.println(result.toString());
		}
	}
}
