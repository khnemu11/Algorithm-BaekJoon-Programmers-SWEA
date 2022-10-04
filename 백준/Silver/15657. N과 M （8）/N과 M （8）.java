import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());

		int num[] = new int[n];
		visited = new boolean[n];
		int[] next = new int[m];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.valueOf(st.nextToken());
		}
		Arrays.sort(num);

		for (int i = 0; i < n; i++) {
			next[0] = num[i];
			dfs(num, next, 1, m);
			visited[i] = true;
		}

		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int[] num, int[] next, int depth, int m) {
		if (depth == m) {
			for (int i = 0; i < next.length; i++) {
				System.out.print(next[i] + " ");
			}
			System.out.println();
		} else {
			for (int i = 0; i < num.length; i++) {
				if (visited[i] || next[depth - 1] > num[i]) {
					continue;
				}
				next[depth] = num[i];
				dfs(num, next, depth + 1, m);
				next[depth] = 0;

			}
		}
	}
}
