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
		StringBuilder sb = new StringBuilder();
		for (int i = N; i > 0; i--) {
			for (int blink = N - i; blink > 0; blink--) {
				sb.append(" ");
			}
			for (int star = i; star > 0; star--) {
				sb.append("*");
			}

			bw.write(sb.toString());
			bw.newLine();

			sb.setLength(0);
		}

		bw.close();
		br.close();
	}

}
