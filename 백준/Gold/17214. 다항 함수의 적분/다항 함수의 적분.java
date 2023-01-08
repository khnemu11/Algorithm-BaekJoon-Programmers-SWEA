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
		String input = br.readLine();

		StringBuilder result = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '+') {
				continue;
			}
			if (input.charAt(i) == 'x') {
				int val = Integer.valueOf(sb.toString()) / 2;
				if (val == 1) {
					result.append("xx+");
				} else if (val == -1) {
					result.append("-xx+");
				} else {
					result.append(val + "xx+");
				}
				sb.setLength(0);
			} else if (input.charAt(i) == '-' && result.length() > 0) {
				result.deleteCharAt(result.length() - 1);
				sb.append(input.charAt(i));
			} else {
				sb.append(input.charAt(i));
			}
		}
		if (sb.length() > 0 && !sb.toString().equals("0")) {
			if (sb.toString().equals("1")) {
				result.append("x+");
			} else if (sb.toString().equals("-1")) {
				result.append("-x+");
			} else {
				result.append(sb.toString() + "x+");
			}
		}

		result.append("W");
		bw.write(result.toString());
		bw.newLine();
		bw.flush();
	}
}
