import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int t = 1; t <= T; t++) {
			int alpha[] = new int[26];
			String a = br.readLine();

			for (int i = 0; i < a.length(); i++) {
				alpha[a.charAt(i) - 'a']++;
			}
			String b = br.readLine();

			for (int i = 0; i < b.length(); i++) {
				alpha[b.charAt(i) - 'a']--;
			}
			int sum = 0;
			for (int i = 0; i < alpha.length; i++) {
				sum += Math.abs(alpha[i]);
			}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #").append(t).append(": ").append(sum);
			bw.write(sb.toString());
			bw.newLine();
		}
		bw.flush();
	}

}
