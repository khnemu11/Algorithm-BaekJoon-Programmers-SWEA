
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int length = sc.nextInt();
			Queue<String> left = new LinkedList<>();
			Queue<String> right = new LinkedList<>();
			Queue<String> perfectShuffle = new LinkedList<>();

			for (int i = 0; i < (double) length / 2; i++) {
				left.add(sc.next());
			}

			for (int i = 0; i < length / 2; i++) {
				right.add(sc.next());
			}
			while (!left.isEmpty() && !right.isEmpty()) {
				perfectShuffle.add(left.poll());
				perfectShuffle.add(right.poll());
			}
			while (!left.isEmpty()) {
				perfectShuffle.add(left.poll());
			}

			StringBuilder result = new StringBuilder();
			result.append("#").append(test_case).append(" ");
			for (int i = 0; i < length; i++) {
				result.append(perfectShuffle.poll()).append(" ");
			}

			System.out.println(result.deleteCharAt(result.length() - 1).toString());
		}
	}
}
