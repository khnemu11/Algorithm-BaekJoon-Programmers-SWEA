import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] steps;
	static boolean[] visited;
	static int[] sum;
	static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		steps = new int[300];
		visited = new boolean[300];
		sum = new int[300];

		for (int i = 0; i < N; i++) {
			steps[i] = Integer.valueOf(br.readLine());
		}


		visited[0] = true;
		sum[0] = steps[0];
		visited[1] = true;
		sum[1] = steps[1] + steps[0];
		
		max = stepScore(N - 1);

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

	public static int stepScore(int depth) {
		if(depth<0) {
			return 0;
		}
		if (visited[depth]) {
			return sum[depth];
		} else {
			int pattern1 = stepScore(depth - 2) + steps[depth];
			int pattern2 = stepScore(depth - 3) + steps[depth] + steps[depth - 1];
			
			visited[depth] = true;
			sum[depth] = Math.max(pattern1, pattern2);
			
			return sum[depth];
		}
	}
}
