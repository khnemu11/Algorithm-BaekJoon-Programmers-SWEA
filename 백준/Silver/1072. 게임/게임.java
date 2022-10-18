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

		StringTokenizer st = new StringTokenizer(br.readLine());
		long x = Long.valueOf(st.nextToken());
		long y = Long.valueOf(st.nextToken());
		long z = 100 * y / x;

		if (z >= 99 || y == x) {
			bw.write("-1");
		} else {
			long resultTop = (z + 1) * x - 100 * y;
			long resultBottom = 100 - (z + 1);
			if (resultTop % resultBottom == 0) {
				bw.write(String.valueOf(resultTop / resultBottom));
			} else {
				bw.write(String.valueOf(resultTop / resultBottom + 1));
			}
		}

		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}