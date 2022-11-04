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
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		int count = 0;

		if (N == 1) {
			count = 1;
		} else if (N == 2) {
			if (M <= 2) {
				count = 1;
			} else if (M <= 4) {
				count = 2;
			} else if (M <= 6) {
				count = 3;
			} else {
				count = 4;
			}
		} else {
			if (M <= 4) {
				count = M;
			}else {
				if (M <= 6) {
					count = 4;
				} else if (M == 7) {
					count = 5;
				} else {
					count = M - 2;
				}
			}
		}

		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
