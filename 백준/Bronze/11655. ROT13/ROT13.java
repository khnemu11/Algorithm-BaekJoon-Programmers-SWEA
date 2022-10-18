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
		char input[] = br.readLine().toCharArray();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.length; i++) {
			if (!Character.isAlphabetic(input[i])) {
				sb.append(input[i]);
				continue;
			}
			if (Character.isLowerCase(input[i])) {
				input[i] = (char) ((int) input[i] + 13);
				if (input[i] > 'z') {
					input[i] = (char) ((int) input[i] - 26);
				}
			} else {
				input[i] = (char) ((int) input[i] + 13);
				if (input[i] > 'Z') {
					input[i] = (char) ((int) input[i] - 26);
				}
			}
			sb.append(input[i]);
		}

		bw.write(sb.toString());
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}