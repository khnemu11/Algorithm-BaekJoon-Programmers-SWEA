
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static Food foodArr[];
	static long max;

	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int L = sc.nextInt();
			foodArr = new Food[N];
			max = 0;

			for (int i = 0; i < N; i++) {
				foodArr[i] = new Food(sc.nextInt(), sc.nextInt());
			}

			dfs(L, 0, 0, 0, 0);

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(max);
			System.out.println(result.toString());
		}
	}

	public static void dfs(int limit, int scoreSum, int calorySum, int curr, int depth) {

		if (curr >= foodArr.length) {
			max = Math.max(scoreSum, max);
		} else {

			for (int i = curr; i < foodArr.length; i++) {

				if (calorySum + foodArr[i].calory <= limit) {
					dfs(limit, scoreSum + foodArr[i].score, calorySum + foodArr[i].calory, i + 1, depth + 1);
				} else {

					max = Math.max(scoreSum, max);
				}
			}
		}

	}
}

class Food {
	int calory;
	int score;

	public Food(int score, int calory) {
		this.calory = calory;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Food [calory=" + calory + ", score=" + score + "]";
	}

}
