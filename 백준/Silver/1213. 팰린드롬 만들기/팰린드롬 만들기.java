import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int alpha[] = new int[26];

		String input = br.readLine();

		for (int i = 0; i < input.length(); i++) {
			alpha[input.charAt(i) - 'A']++;
		}

		int odd = 0;

		for (int i = 0; i < alpha.length; i++) {
			if (alpha[i] % 2 == 1) {
				odd++;
			}
		}

		if ((input.length() % 2 == 0 && odd > 0) || (input.length() % 2 == 1 && odd > 1)) {
			bw.write("I'm Sorry Hansoo");
		} else {
			if (input.length() % 2 == 0) {
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < alpha.length; i++) {
					if (alpha[i] > 0) {
						for (int k = 0; k < alpha[i] / 2; k++) {
							sb.append((char) (i + 'A'));
						}
					}
				}

				bw.write(sb.toString() + sb.reverse().toString());
			} else {
				StringBuilder sb = new StringBuilder();
				char mid = ' ';

				for (int i = 0; i < alpha.length; i++) {
					if (alpha[i] > 0) {
						if (alpha[i] % 2 == 1) {
							mid = (char) (i + 'A');
						}

						for (int k = 0; k < alpha[i] / 2; k++) {
							sb.append((char) (i + 'A'));
						}
					}
				}

				bw.write(sb.toString() + mid + sb.reverse().toString());
			}
		}
		
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
