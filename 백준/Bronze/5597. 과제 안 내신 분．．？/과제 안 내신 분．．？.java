import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		boolean submit[] = new boolean[31];

		for (int i = 0; i < 28; i++) {
			submit[Integer.valueOf(br.readLine())] = true;
		}

		int count = 0;

		for (int i = 1; i <= 30 && count < 2; i++) {
			if (!submit[i]) {
				bw.write(String.valueOf(i));
				bw.newLine();
				count++;
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
