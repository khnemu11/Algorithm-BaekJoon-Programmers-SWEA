import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String words = br.readLine();
		StringBuilder result = new StringBuilder();
		StringBuilder word = new StringBuilder();

		for (int i = 0; i < words.length(); i++) {
			if (words.charAt(i) == '.' && word.length() % 2 == 1) {
				bw.write("-1");
				result.setLength(0);
				word.setLength(0);
				break;
			} else if (words.charAt(i) == '.') {
				for (int j = 0; j < word.length() / 4; j++) {
					result.append("AAAA");
				}
				if (word.length() % 4 > 0) {
					result.append("BB");
				}
				result.append(".");
				word.setLength(0);
			} else {
				word.append(words.charAt(i));
			}
		}
		if (word.length() > 0) {
			if (word.length() % 2 == 1) {
				bw.write("-1");
			} else {
				for (int j = 0; j < word.length() / 4; j++) {
					result.append("AAAA");
				}
				if (word.length() % 4 > 0) {
					result.append("BB");
				}
				bw.write(result.toString());
			}
		}
		else {
			bw.write(result.toString());
		}
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
