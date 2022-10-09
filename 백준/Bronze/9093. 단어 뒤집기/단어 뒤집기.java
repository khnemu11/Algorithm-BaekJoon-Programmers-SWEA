import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int i = 0; i < T; i++) {
			String[] strs = br.readLine().split(" ");

			for (int j = 0; j < strs.length; j++) {
				StringBuilder sb = new StringBuilder(strs[j]);
				bw.write(sb.reverse().toString());
				bw.write(" ");
			}
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}
}