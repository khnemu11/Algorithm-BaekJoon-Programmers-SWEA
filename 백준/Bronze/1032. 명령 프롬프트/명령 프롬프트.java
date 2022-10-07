import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.valueOf(br.readLine());
		String pattern = br.readLine();

		for (int i = 0; i < num - 1; i++) {
			String next = br.readLine();
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < next.length(); j++) {
				if (next.charAt(j) == pattern.charAt(j)) {
					sb.append(next.charAt(j));
				} else {
					sb.append("?");
				}
			}
			pattern = sb.toString();
		}

		bw.write(pattern);
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
