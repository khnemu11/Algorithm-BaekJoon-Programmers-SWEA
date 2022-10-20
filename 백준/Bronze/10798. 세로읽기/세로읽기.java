import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char words[][] = new char[5][15];
		int length[] = new int[5];

		for (int i = 0; i < 5; i++) {
			String word = br.readLine();
			words[i] = word.toCharArray();
			length[i] = word.length();
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 5; j++) {
				if (length[j] - 1 < i) {
					continue;
				}
				sb.append(words[j][i]);
			}
		}
		bw.write(sb.toString());
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}