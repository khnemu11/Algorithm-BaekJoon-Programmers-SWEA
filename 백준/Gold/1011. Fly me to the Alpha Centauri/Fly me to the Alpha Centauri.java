import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());

			int goal = y - x;
			int max = (int) Math.sqrt(goal);
			if (goal == max * max) {
				bw.write(String.valueOf(2 * max - 1));
			} else if (goal <= max * max + max) {
				bw.write(String.valueOf(2 * max));
			} else {
				bw.write(String.valueOf(2 * max + 1));
			}
			bw.newLine();
		}
		bw.close();
		br.close();
	}

}