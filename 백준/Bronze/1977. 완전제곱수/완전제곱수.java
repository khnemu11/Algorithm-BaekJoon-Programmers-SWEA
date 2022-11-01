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
		int M = Integer.valueOf(br.readLine());

		int start = N % (int) Math.sqrt(N) == 0 ? (int) Math.sqrt(N) : (int) Math.sqrt(N) + 1;
		int sum = 0;

		for (int i = start; i * i <= M; i++) {
			sum += i * i;
		}

		if (sum == 0) {
			bw.write("-1");
			bw.newLine();
		} else {
			bw.write(String.valueOf(sum));
			bw.newLine();
			bw.write(String.valueOf(start * start));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
