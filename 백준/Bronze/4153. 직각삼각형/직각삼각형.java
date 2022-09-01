import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int[] lengths = new int[3];

		while (true) {
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < 3; i++) {
				lengths[i] = Integer.valueOf(st.nextToken());
			}

			if (lengths[0] == 0 && lengths[1] == 0 && lengths[2] == 0) {
				break;
			}

			int max = Arrays.stream(lengths).max().getAsInt();

			double twoSquare = 0;

			for (int i = 0; i < 3; i++) {
				if (lengths[i] == max)
					continue;
				twoSquare += Math.pow(lengths[i], 2);
			}

			if (twoSquare == Math.pow(max, 2)) {
				bw.write("right");
			} else {
				bw.write("wrong");
			}
			bw.newLine();
		}

		bw.flush();
		br.close();
		bw.close();
	}
}