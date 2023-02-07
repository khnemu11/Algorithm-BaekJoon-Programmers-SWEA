import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	풀이 과정
	2~5까지가 펠린드롬인가 -> 2와 5가 같은 숫자이고 3~4까지가 펠린드롬인가 -> 2와 5가 같은 숫자이고 3,4가 같은 숫자인가 -> dp
	dp 배열 최대 크기 : 2000*2000
*/

public class Main {
	static boolean dp[][];
	static boolean visited[][];
	static int nums[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.valueOf(br.readLine());
		dp = new boolean[size][size];
		visited = new boolean[size][size];
		nums = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int M = Integer.valueOf(br.readLine());

		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());

			if (isPallindrome(from - 1, to - 1)) {
				bw.write("1");
			} else {
				bw.write("0");
			}
			bw.newLine();
		}

		bw.flush();
	}

	public static boolean isPallindrome(int from, int to) {
		if (to < from) {
			return true;
		}
		if (visited[from][to]) {
			return dp[from][to];
		}

		visited[from][to] = true;

		if (from == to) {
			dp[from][to] = true;
			return dp[from][to];
		} else {
			if (isPallindrome(from + 1, to - 1) && nums[from] == nums[to]) {
				dp[from][to] = true;
			} else {
				dp[from][to] = false;
			}
			return dp[from][to];
		}
	}
}
