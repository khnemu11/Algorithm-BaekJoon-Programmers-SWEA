import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		if (N == 1) {
			bw.write("*");
			bw.newLine();
		} else {
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();

			for (int i = 1; i <= N; i++) {
				if (i % 2 == 1) {
					sb1.append("*");
					sb2.append(" ");
				} else {
					sb1.append(" ");
					sb2.append("*");
				}
			}

			for (int i = 0; i < N; i++) {
				bw.write(sb1.toString());
				bw.newLine();
				bw.write(sb2.toString());
				bw.newLine();
			}
		}

		
		bw.flush();
		bw.close();
		br.close();
	}

}
