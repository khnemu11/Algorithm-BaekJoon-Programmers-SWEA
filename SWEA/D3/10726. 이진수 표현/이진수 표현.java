import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int M = Integer.valueOf(st.nextToken());
			String binary = Integer.toBinaryString(M);
			boolean isAllOn = true;
			for (int i = binary.length() - N; i < binary.length(); i++) {
				if (i < 0 || binary.charAt(i) == '0') {
					isAllOn = false;
					break;
				}
			}

			if (isAllOn) {
				bw.write("#" + testcase + " ON\n");
			}else {
				bw.write("#" + testcase + " OFF\n");
			}
		}

		bw.flush();
		bw.close();
	}
}