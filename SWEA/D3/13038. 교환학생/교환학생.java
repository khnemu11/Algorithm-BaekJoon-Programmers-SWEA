
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int goal = sc.nextInt();
			int schedule[] = new int[7];

			int min = Integer.MAX_VALUE;
			Queue<Integer> queue = new LinkedList<>();
			for (int i = 0; i < 7; i++) {
				schedule[i] = sc.nextInt();
				if (schedule[i] == 1) {
					queue.add(i);
				}
			}

			while (!queue.isEmpty()) {
				int curr = queue.poll();
				int count = 0;
				int lectureCount = 0;

				while (goal != lectureCount) {
					if (schedule[curr] == 1) {
						lectureCount++;
					}

					count++;
					curr++;

					if (curr >= 7) {
						curr = 0;
					}
				}

				min = Math.min(min, count);
			}

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(min);
			System.out.println(result.toString());
		}
	}
}