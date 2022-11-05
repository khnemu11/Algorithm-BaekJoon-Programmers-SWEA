import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine();
		StringBuilder sb = new StringBuilder();

		if (input.length() % 3 != 0) {
			int rest = 3 - input.length() % 3;
			while (rest-- > 0) {
				sb.append("0");
			}

			input = sb.append(input).toString();
		}
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < input.length(); i += 3) {
			int digit = (input.charAt(i) - '0') * 4 + (input.charAt(i + 1) - '0') * 2 + (input.charAt(i + 2) - '0');
			result.append(digit);
		}
		bw.write(result.toString());
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
