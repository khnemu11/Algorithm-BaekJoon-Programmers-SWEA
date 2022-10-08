import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int total = Integer.valueOf(st.nextToken());
		int a = Integer.valueOf(st.nextToken());
		int b = Integer.valueOf(st.nextToken());
		int count = 1;

		while (a != b) {
			if (a % 2 == 0) {
				a = a / 2;
			} else {
				a = (a + 1) / 2;
			}
			if (b % 2 == 0) {
				b = b / 2;
			} else {
				b = (b + 1) / 2;
			}
			count++;
		}
		bw.write(String.valueOf(count - 1));
		bw.flush();
		bw.close();
		br.close();
	}
}
