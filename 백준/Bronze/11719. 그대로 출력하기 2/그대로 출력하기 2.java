import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input="";
		
		while((input = br.readLine())!=null) {
			bw.write(input);
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean dfs(int[] arr, int depth, int sum) {
		if (depth == 7) {
			if (sum == 100) {
				return true;
			} else {
				return false;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			if (dfs(arr, depth + 1, sum + arr[i])) {
				return true;
			} else {
				visited[i] = false;
			}
		}

		return false;
	}
}
