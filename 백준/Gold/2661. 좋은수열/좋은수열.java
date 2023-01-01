import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static String goodStr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		goodStr = "";
		int length = Integer.valueOf(br.readLine());
		findGoodArr(new StringBuilder(), length);
		bw.write(goodStr);
		bw.newLine();
		bw.flush();
	}

	public static void findGoodArr(StringBuilder sb, int length) {
		if (sb.length() == length) {
			goodStr = sb.toString();
		} else {
			for (int add = 1; add < 4; add++) {
				if (goodStr.isEmpty()) {
					sb.append(add);
					String target = sb.toString();
					boolean isValid = true;
					for (int len = 1; len <= target.length(); len++) {
						for (int i = 0; i + len + len <= target.length(); i++) {
							if (target.substring(i, i + len).equals(target.substring(i + len, i + len + len))) {
								isValid = false;
								break;
							}
						}
						if (!isValid) {
							break;
						}
					}
					if (isValid) {
						findGoodArr(sb, length);
					}
					sb.deleteCharAt(sb.length() - 1);
				}

			}
		}
	}
}
