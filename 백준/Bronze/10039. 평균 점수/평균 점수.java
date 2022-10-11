import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			int score = Integer.valueOf(br.readLine());

			if (score < 40) {
				sum += 40;
			} else {
				sum += score;
			}
		}
		bw.write(String.valueOf(sum / 5));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
