import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static boolean visited[];
	static int num[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int size = sc.nextInt();
			int target = sc.nextInt();

			num = new int[size];
			visited = new boolean[size];
			
			for(int i=0;i<size;i++) {
				num[i] =sc.nextInt();
			}
			
			int count = dfs(target,0,0);
			
			StringBuilder result = new StringBuilder();
			result.append("#").append(test_case).append(" ").append(count);
			System.out.println(result.toString());
		}
	}

	public static int dfs(int target, int currIndex, int sum) {
		int count = 0;
		for (int i = currIndex; i < visited.length; i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			
			if (sum + num[i] < target) {
				count += dfs(target, i + 1, sum + num[i]);
			} else if (target == sum + num[i]) {
				count++;
			}
			visited[i] = false;
		}

		return count;
	}
}
